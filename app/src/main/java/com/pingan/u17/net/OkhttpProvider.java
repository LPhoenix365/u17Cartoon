package com.pingan.u17.net;

import android.text.TextUtils;

import com.pingan.u17.base.U17Application;
import com.pingan.u17.util.ToolUtils;
import com.pingan.u17.util.U17Log;
import com.trello.rxlifecycle2.internal.Preconditions;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by _SOLID
 * Date:2016/12/3
 * Time:13:44
 */

public class OkHttpProvider {

    private final static long DEFAULT_CONNECT_TIMEOUT = 5;
    private final static long DEFAULT_WRITE_TIMEOUT = 20;
    private final static long DEFAULT_READ_TIMEOUT = 10;

    public static OkHttpClient getDefaultOkHttpClient() {
        return getOkHttpClient(new CacheControlInterceptor());
    }

    public static OkHttpClient getOkHttpClient() {
        return getOkHttpClient(new FromNetWorkControlInterceptor());
    }

    private static OkHttpClient getOkHttpClient(Interceptor cacheControl) {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        //设置缓存
        File httpCacheDirectory = new File(U17Application.getInstance().getCacheDir(), "OkHttpCache");
        httpClientBuilder.cache(new Cache(httpCacheDirectory, 100 * 1024 * 1024));
        //设置拦截器
        httpClientBuilder.addInterceptor(new UserAgentInterceptor("Android Device"));
        httpClientBuilder.addInterceptor(new paraInterceptor());
        httpClientBuilder.addInterceptor(new LoggingInterceptor());
        httpClientBuilder.addInterceptor(cacheControl);
        httpClientBuilder.addNetworkInterceptor(cacheControl);
        return httpClientBuilder.build();
    }

    /**
     * 没有网络的情况下就从缓存中取
     * 有网络的情况则从网络获取
     */
    private static class CacheControlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!ToolUtils.isConnected(U17Application.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response response = chain.proceed(request);
            if (ToolUtils.isConnected(U17Application.getInstance())) {
                int maxAge = 60;//在有网络连接的情况下，一分钟内不再请求网络
                String cacheControl = request.cacheControl().toString();
                if (TextUtils.isEmpty(cacheControl)) {
                    cacheControl = "public, max-age=" + maxAge;
                }
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", cacheControl)
                        .build();

            } else {    //没网时保留30天
                int maxStale = 60 * 60 * 24 * 30;
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    }

    /**
     * 强制从网络获取数据
     */
    private static class FromNetWorkControlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();

            Response response = chain.proceed(request);

            return response;
        }
    }


    private static class UserAgentInterceptor implements Interceptor {
        private static final String USER_AGENT_HEADER_NAME = "User-Agent";
        private final String userAgentHeaderValue;

        UserAgentInterceptor(String userAgentHeaderValue) {
            this.userAgentHeaderValue = Preconditions.checkNotNull(userAgentHeaderValue, "userAgentHeaderValue = null");
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request originalRequest = chain.request();
            final Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader(USER_AGENT_HEADER_NAME)
                    .addHeader(USER_AGENT_HEADER_NAME, userAgentHeaderValue)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }

    private static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            U17Log.i(this, String.format(Locale.CHINA, "Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            U17Log.i(this, String.format(Locale.CHINA, "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

    private static final String KEY = "db307601d4ead16fd06db70337a8a7b02192fab835e8da40342a67af49e34ec4c5cde50aa0321fd5cd6dfae688dcf62fb84" +
            "cda7763820dda7fcbf3a0b7b7863ec52f5ab7638d7737907a39f23f3b3c9c88fbdf79be9f93e4b854c2cb18978d5fc1f07d60362b9bd539ed663284da7a6e%253A%253A%253Aopen";
    private static final String ANDROID_ID = "204e7c5d9fa8f036";

    private static class paraInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            HttpUrl.Builder httpUrl = originalRequest
                    .url()
                    .newBuilder()
                    .addQueryParameter("v", String.valueOf(ToolUtils.getVersionCode()))
                    .addQueryParameter("key", KEY)
                    .addQueryParameter("android_id", ANDROID_ID)
                    .addQueryParameter("come_from", ToolUtils.getBrand())
                    .addQueryParameter("model", ToolUtils.getPhoneModel());
            Response response = chain.proceed(originalRequest.newBuilder().url(httpUrl.build()).build());
            return response;
        }
    }
}
