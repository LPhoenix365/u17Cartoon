package com.pingan.u17.base;

import android.app.Application;
import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.pingan.u17.net.HttpClient;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Description
 * @author  liupeng502
 * @data    2017/8/4
 */
public class U17Application extends Application {

    private static U17Application INSTANCE;
    private final static String ApiId = "api88888888";
    private IWXAPI     mWxapi;
    private HttpClient mHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Fresco.initialize(this);
        regToWx();
        //setHttpClient(new HttpClient());
    }

    public static U17Application getInstance() {
        return INSTANCE;
    }

    public void setHttpClient(HttpClient httpClient) {
        mHttpClient = httpClient;
    }

    public HttpClient getHttpClient() {
        return mHttpClient;
    }

    //注册到微信
    private void regToWx() {
        mWxapi = WXAPIFactory.createWXAPI(INSTANCE, ApiId, true);
        mWxapi.registerApp(ApiId);
    }


    class initIntentService extends IntentService{
        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         *
         * @param name Used to name the worker thread, important only for debugging.
         */
        public initIntentService(String name) {
            super(name);
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {

        }
    }

}
