package rst.framework.download;

/**
 * Created by iceman on 16/11/24.
 */

public class DownloadParams {
    public String url;
    public boolean autoDelete = false;
    public String fileName;

    public DownloadParams(String url) {
        this.url = url;
        fileName = url.substring(url.lastIndexOf("/") + 1);
    }

}
