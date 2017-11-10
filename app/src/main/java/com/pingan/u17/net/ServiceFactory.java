package com.pingan.u17.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/8
 */

public class ServiceFactory {
    private final Gson mGson;
    private OkHttpClient mOkHttpClient;


    private ServiceFactory() {
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        //默认使用缓存
        mOkHttpClient = OkHttpProvider.getDefaultOkHttpClient();
    }

    public static ServiceFactory getNoCacheInstance() {
        ServiceFactory factory = SingletonHolder.INSTANCE;
        factory.mOkHttpClient = OkHttpProvider.getOkHttpClient();
        return factory;
    }

    private static class SingletonHolder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public <S> S createService(Class<S> serviceClass) {
        String baseUrl ="";
        try {
            Field field = serviceClass.getField("BASE_URL");
             baseUrl = (String) field.get(serviceClass);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)//生产者模式提供多种okhttpclient
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }
}
