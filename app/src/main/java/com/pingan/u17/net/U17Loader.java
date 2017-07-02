package com.pingan.u17.net;

import com.pingan.u17.base.U17Application;
import com.pingan.u17.bean.UpdateBean;

import rx.Observable;
import rx.functions.Func1;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 *
 * 此处loader已经不需要 功能由mvp中的P和M代替
 */

public class U17Loader extends ObjectLoader {


    private RestApi api;
    public U17Loader() {
        api = U17Application.getInstance().getHttpClient().getApiService();
    }

    /**
     * @return
     */
    public Observable<String> hasNewversion(String t, String model, String android_id) {
        return observe(api.hasNewversion(t, model, android_id)
                .map(new Func1<UpdateBean, String>() {
                    @Override
                    public String call(UpdateBean updateBean) {
                        return null;
                    }
                }));
    }

    public Observable<String> getWeatherList(String cityId, String key) {
        return observe(api.getWeather(cityId, key))
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        //可以处理对应的逻辑后在返回
                        return s;
                    }
                });
    }

}
