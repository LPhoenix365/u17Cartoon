package rst.framework.download;

import rst.framework.task.BaseTask;

/**
 * Created by iceman on 16/11/24.
 * 下载文件
 */

public class DownloadTask {
    private DownloadParams downloadParams;
    private DownloadCallback callback;

    public DownloadTask(DownloadParams downloadParams, DownloadCallback callback) {
        super();
        this.downloadParams = downloadParams;
        this.callback = callback;
    }

    public String getDownloadUrl() {
        return downloadParams.url;
    }

    public String getFileName() {
        return downloadParams.fileName;
    }

    public DownloadCallback getCallback() {
        return callback;
    }
}
