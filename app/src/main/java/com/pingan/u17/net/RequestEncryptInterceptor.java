package com.pingan.u17.net;

import com.pingan.u17.util.ToolUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Description  使用拦截器可以 实现缓存 添加header 参数加密  添加固定参数
 *
 * @author liupeng502
 * @data 2017/6/27
 */

public class RequestEncryptInterceptor implements Interceptor {


    private static final String FORM_NAME = "content";
    private static final String CHARSET = "UTF-8";


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody body = request.body();

        if (body instanceof FormBody) {
            FormBody formBody = (FormBody) body;
            Map<String, String> formMap = new HashMap<>();
            // 从 formBody 中拿到请求参数，放入 formMap 中
            for (int i = 0; i < formBody.size(); i++) {
                formMap.put(formBody.name(i), formBody.value(i));
            }
            //加入接口请求固定参数 token uerID...
            formMap.put("version",ToolUtils.getVersionName());
            formMap.put("deviceId",ToolUtils.getDeviceId());

            // 将formMap转化为json 然后AES加密
           /* Gson gson = new Gson();
            String jsonParams = gson.toJson(formMap);
            String encryptParams = null;//AESCryptUtils.encrypt(jsonParams.getBytes(CHARSET), AppConstant.getAESKey());*/
            // 重新修改 body 的内容
            body = new FormBody.Builder()
                    .add("version",ToolUtils.getVersionName())
                    .build();
        }
        if (body != null) {
            request = request.newBuilder()
                    .post(body)
                    .build();
        }
        return chain.proceed(request);
    }
}
