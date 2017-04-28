package rst.framework.network;


import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by iceman on 16/10/27 16:50
 * 邮箱：xubin865@pingan.com.cn
 * 用于表单提交
 */

public class MultiPartServiceParams extends ServiceParams {
    protected ArrayList<MultiPart> multiParts = new ArrayList<>();
    protected ArrayList<String[]> params = new ArrayList<>();

    public MultiPartServiceParams(String url, Class responsType) {
        super(url, responsType);
    }

    public MultiPartServiceParams addMultiPart(MultiPart part) {
        multiParts.add(part);
        return this;
    }

    public MultiPartServiceParams addParams(String key, String value) {
        params.add(new String[]{key, value});
        return this;
    }


    @Override
    public Request getRequest(String tag) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (int i = 0; i < multiParts.size(); i++) {
            MultiPart file = multiParts.get(i);
            if (TextUtils.isEmpty(file.getFilepath())) {
                builder.addFormDataPart(file.getName(), file.getFilename(), RequestBody.create(MediaType.parse(file.getMime()), file.getFiledata()));
            } else {
                builder.addFormDataPart(file.getName(), file.getFilename(), RequestBody.create(MediaType.parse(file.getMime()), new File(file.getFilepath())));
            }
        }
        for (String[] param : params) {
            builder.addFormDataPart(param[0], param[1]);
        }
        return new okhttp3.Request.Builder()
                .url(url).headers(Headers.of(headers)).post(builder.build())
                .tag(tag)
                .build();
    }
}
