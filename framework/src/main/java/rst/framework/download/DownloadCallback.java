package rst.framework.download;

/**
 * Created by iceman on 16/11/24.
 */

public interface DownloadCallback {
    void onProgress(int progress);

    void onSuccess(String path);

    void onFailure();
}
