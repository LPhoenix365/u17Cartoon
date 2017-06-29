package com.pingan.u17.net;

import com.pingan.u17.base.U17Application;
import com.pingan.u17.util.AppEnvConstants;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/5/31
 */
public class HttpClient {
    private        OkHttpClient okHttpClient;
    private        Object       apiService;


    private Cache initCache() {
        Cache cache = null;
        File cacheFile = new File(U17Application.getInstance().getCacheDir(), U17Application.getInstance().getPackageName() + "cacheFile");
        if (cache == null)
            cache = new Cache(cacheFile, 20 * 1024 * 1024);  //20M
        return cache;
    }


    public RestApi getApiService() {
        return (RestApi)apiService;
    }

    public<T> T getApi(Class<T> api){
        return (T) apiService;
    }

    /**
     * 使用默认配置服务
     */
    public HttpClient() {
        Builder builder = new Builder();
        //这里设置的build 仅仅是层封装
        builder.setInterceptor(new RequestEncryptInterceptor())
                .setRestfulApi(RestApi.class)
                .setCache(initCache())
                .setHostIp(AppEnvConstants.BASE_URL);
        createHttpClinet(builder);
    }

    private void createHttpClinet(Builder builder) {
        okHttpClient = genericClient(builder);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(builder.getHostIp())
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        apiService = retrofit.create(builder.getRestfulApi());
    }
    // 创建OkHttpClient实例
    private OkHttpClient genericClient(Builder builder) {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(builder.getCache())
                .addInterceptor(builder.getInterceptor());
        return httpBuilder.build();
    }


}
