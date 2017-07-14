package com.pingan.u17.net;

import okhttp3.Cache;
import okhttp3.Interceptor;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/28
 */

public  class Builder {

    /**
     * retrofit请求host地址
     */
    private String mHostIp;

    /**
     * retrofit请求拦截器
     */
    private Interceptor mInterceptor;

    /**
     * retrofit请求接口封装类
     */
    private Class apiClass;


    /**
     * okhtttp 设置的缓存
     */
    private Cache cache;

    public Builder setHostIp(String ip){
        mHostIp = ip;
        return this;
    }

    public String getHostIp(){
        return mHostIp;
    }

    public Builder setInterceptor(Interceptor interceptor){
        this.mInterceptor = interceptor;
        return this;
    }

    public Interceptor getInterceptor(){
        return mInterceptor;
    }

    public Builder setRestfulApi(Class apiClass){
        this.apiClass = apiClass;
        return this;
    }

    public Class getRestfulApi(){
        return apiClass;
    }


    public Cache getCache() {
        return cache;
    }

    public Builder setCache(Cache cache) {
        this.cache = cache;
        return this;
    }




}
