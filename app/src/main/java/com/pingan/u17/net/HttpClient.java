package com.pingan.u17.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/5/31
 */

public class HttpClient  {

    public HttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(null)
                .proxy(null)
                .authenticator(null)
                .build();


        OkHttpClient clientWith60sTimeout = client.newBuilder().
                readTimeout(60, TimeUnit.SECONDS).
                build();
    }

    public Request createRequest(){
        return   new Request.Builder()
                .addHeader("Token", "")
                .addHeader("UserId", "")
                .url("")
                //replacing any cache control headers already
                .cacheControl(null)
                .build();
    }

    public Response getRespone(){
        new Response.Builder();
        return null;
    }
}
