package rst.framework.network;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Request;

/**
 * Created by iceman on 16/10/27 16:50
 * 邮箱：xubin865@pingan.com.cn
 * 普通post请求
 */

public class PostServiceParams extends ServiceParams {
    protected HashMap<String, String> postMap;

    public PostServiceParams(String url, Class responsType) {
        super(url, responsType);
    }


    public PostServiceParams setPostMap(HashMap<String, String> postmap) {
        this.postMap = postmap;
        return this;
    }

    @Override
    public Request getRequest(String tag) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> param : postMap.entrySet()) {
            builder.add(param.getKey(), param.getValue());
        }
        return new okhttp3.Request.Builder()
                .url(url).headers(Headers.of(headers)).post(builder.build())
                .tag(tag)
                .build();
    }
}
