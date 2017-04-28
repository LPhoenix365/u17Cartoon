package rst.framework.network;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by iceman on 16/10/27 16:50
 * 邮箱：xubin865@pingan.com.cn
 * post方式提交json
 */

public class PostJsonServiceParams extends ServiceParams {
    private String jsonEntity;

    public PostJsonServiceParams(String url, Class responsType) {
        super(url, responsType);
    }

    public PostJsonServiceParams setJsonEntity(String jsonEntity) {
        this.jsonEntity = jsonEntity;
        return this;
    }


    @Override
    public Request getRequest(String tag) {
        return new okhttp3.Request.Builder()
                .url(url).headers(Headers.of(headers)).post(RequestBody.create(MediaType.parse("application/json"), jsonEntity))
                .tag(tag)
                .build();
    }
}
