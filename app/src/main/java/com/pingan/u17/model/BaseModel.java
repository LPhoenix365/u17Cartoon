package com.pingan.u17.model;

import com.pingan.u17.base.U17Application;
import com.pingan.u17.net.RestApi;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public class BaseModel {

    public RestApi api;

    public BaseModel() {
        api = U17Application.getInstance().getHttpClient().getApiService();
    }

    /**
     *
     * @param observable
     * @param
     * @return
     */
    protected   Observable observe(Observable observable){
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
