package com.pingan.u17.net;

import android.util.Log;

import com.pingan.u17.base.U17Application;
import com.pingan.u17.util.ToolUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description  这种拦截器合适 服务器返回 下面这中类型的缓存
 * Cache-Control →no-cache, private
 * Content-Encoding →gzip
 * Content-Type →application/json
 * Date →Tue, 27 Jun 2017 09:31:45 GMT
 * Keep-Alive →timeout=38
 * Pragma →no-cache
 * <p>
 * <p> 这种接口返回的 直接设置缓存目录就可以了
 * Cache-Control →max-age=300,stale-while-revalidate=2592000,cache, private
 * Content-Encoding →gzip
 * Content-Type →application/json
 * Date →Tue, 27 Jun 2017 09:32:39 GMT
 * Expires →Tue, 27 Jun 2017 09:32:39 GMT
 * Keep-Alive →timeout=38
 * Last-Modified →Tue, 27 Jun 2017 09:32:04 GMT
 * Pragma →, no-cache
 *
 * @author liupeng502
 * @data 2017/6/1
 */

public class CustomIntepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Response response1 = response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                //cache for 30 days
                .header("Cache-Control", "max-age=" + 3600 * 24 * 30)
                .build();
        return response1;
    }


    private Interceptor mCacheControlInterceptor = new Interceptor() {
        public int CACHE_STALE_LONG = 6000;
        public int CACHE_AGE_SHORT = 300;

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (ToolUtils.isWifi(U17Application.getInstance())) {
                //没网时只使用缓存
                //自定义请求头，可以在响应头对请求头的header进行拦截，配置不同的缓存策略
                request = request.newBuilder()
                        .header("head-request", request.toString())
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (ToolUtils.isWifi(U17Application.getInstance())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                Log.e("Interceptor", "response: " + response.toString());
                //添加头信息，配置Cache-Control
                //removeHeader("Pragma") 使缓存生效
                return response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + CACHE_AGE_SHORT)
                        .removeHeader("Pragma")
                        .build();
            } else {
                Log.e("Interceptor", "net not connect");
                return response.newBuilder()
                        .header("Cache-Control", "public,only-if-cached, max-stale=" + CACHE_STALE_LONG)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
}
