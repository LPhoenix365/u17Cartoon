package rst.framework.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by iceman on 16/1/21.
 */
public class FileUtil {
    /**
     *  读取文件为byte数组
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    public static String getSdCardRootPath() {
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            LogUtil.e(FileUtil.class.getSimpleName(), "sd卡未加载");
            return "/";
        } else {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
    }

    /**
     * 从文件中读取所有字符串
     *
     * @param filePath
     * @return
     */
    public static String readFromFile(String filePath) {
        StringBuilder builder = new StringBuilder();
        InputStreamReader read = null;
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    builder.append(lineTxt);
                }
            } else {
                LogUtil.e(FileUtil.class.getSimpleName(), "找不到指定文件");
            }
        } catch (Exception e) {
            LogUtil.e(FileUtil.class.getSimpleName(), "读取文件内容出错");
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

    /**
     * 从assets中读取所有字符串
     *
     * @param filePath
     * @return
     */
    public static String readFromAssets(Context context, String filePath) {
        StringBuilder builder = new StringBuilder();
        InputStreamReader read = null;
        try {
            read = new InputStreamReader(
                    context.getAssets().open(filePath));
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                builder.append(lineTxt);
            }
        } catch (Exception e) {
            LogUtil.e(FileUtil.class.getSimpleName(), "读取文件内容出错");
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

    /**
     * 拼接路径
     *
     * @param prePath      http://www.demo.com/wwwroot 或 http://www.demo.com/wwwroot/
     * @param fileNamePath index.html 或 /index.html
     * @return http://www.demo.com/wwwroot/index.html
     * @author EX-LIJINHUA001
     * @date 2013-8-12
     */
    public static String splitJointPath(String prePath, String fileNamePath) {
        if (TextUtils.isEmpty(prePath) && TextUtils.isEmpty(fileNamePath)) {
            return "";
        } else if (TextUtils.isEmpty(prePath) && !TextUtils.isEmpty(fileNamePath)) {
            return fileNamePath;
        } else if (!TextUtils.isEmpty(prePath) && TextUtils.isEmpty(fileNamePath)) {
            return prePath;
        }
        String path = "";
        if (prePath.endsWith("/")) {
            if (fileNamePath.startsWith("/")) {
                path = prePath + fileNamePath.substring(1);
            } else {
                path = prePath + fileNamePath;
            }
        } else {
            if (fileNamePath.startsWith("/")) {
                path = prePath + fileNamePath;
            } else {
                path = prePath + "/" + fileNamePath;
            }
        }
        return path;
    }

    public static String getWebroot(Context context) {
        return context.getFilesDir().getAbsolutePath() + "/webroot";
    }

    public static String getSDCardWebroot(Context context) {
        File f = context.getExternalFilesDir("webroot");
        if (f != null) {
            return f.getAbsolutePath();
        } else {
            return "";
        }
    }

}
