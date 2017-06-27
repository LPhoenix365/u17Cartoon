package com.pingan.u17.net;

import com.pingan.u17.base.U17Application;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/5/31
 */

public class HttpClient {

    private        Cache      mCache;
    private static HttpClient mHttpClient;

    private HttpClient() {
        initCache();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(mCache)
                .addInterceptor(null)
                .build();
    }

    public HttpClient getHttpClient() {
        if (mHttpClient == null) {
            mHttpClient = new HttpClient();
        }
        return mHttpClient;
    }


    private void initCache() {
        File cacheFile = new File(U17Application.getInstance().getCacheDir(), U17Application.getInstance().getPackageName() + "cacheFile");
        mCache = new Cache(cacheFile, 20 * 1024 * 1024);  //20M
    }

    public Request createRequest() {
        return new Request.Builder()
                .addHeader("Token", "")
                .addHeader("UserId", "")
                .url("")
                .cacheControl(null)
                .build();
    }

    public Response getRespone() {
        new Response.Builder();
        return null;
    }
}
