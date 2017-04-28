package rst.framework.task;

import android.app.DownloadManager;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rst.framework.BaseApplication;
import rst.framework.download.DownloadCallback;
import rst.framework.download.DownloadTask;
import rst.framework.network.OkhttpUtil;
import rst.framework.utils.LogUtil;

/**
 * Created by iceman on 16/11/21.
 */

public class DownloadExcuter {
    private static DownloadExcuter downloadExcuter = new DownloadExcuter();
    private HashMap<Long, DownloadTask> downloadTaskHashMap = new HashMap<>();

    private Handler handler;

    private File fileDir;
    private final String DIR_NAME = "download";

    public static DownloadExcuter getInstance() {
        return downloadExcuter;
    }

    private DownloadExcuter() {
        fileDir = BaseApplication.getAppContext().getExternalFilesDir(DIR_NAME);
        handler = new Handler(Looper.getMainLooper());
    }

    public void excuteWithOkhttp(final DownloadTask downloadTask) {
        Request request = new Request.Builder().url(downloadTask.getDownloadUrl()).build();
        OkHttpClient mOkHttpClient = OkhttpUtil.getmOkHttpClient();
        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                LogUtil.e("网络异常:" + downloadTask.getDownloadUrl());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        downloadTask.getCallback().onFailure();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtil.e("onResponse");
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    final File file = new File(fileDir, downloadTask.getFileName());
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    int outProcess = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        final int progress = (int) (sum * 100 / total);
                        if (outProcess == progress) {
                            continue;
                        }
                        outProcess = progress;
                        LogUtil.d("progress=" + progress);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                downloadTask.getCallback().onProgress(progress);
                            }
                        });
                    }
                    fos.flush();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            downloadTask.getCallback().onSuccess(file.getAbsolutePath());
                        }
                    });
                    LogUtil.d("文件下载成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtil.d("文件下载失败");
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
