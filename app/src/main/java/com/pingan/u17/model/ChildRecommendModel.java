package com.pingan.u17.model;

import com.pingan.u17.bean.HomePageBean;
import com.pingan.u17.bean.UpdateBean;
import com.pingan.u17.net.rxImp.Fun1Imp;

import rx.Observable;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public class ChildRecommendModel extends BaseModel {

    /**
     * @return
     */
    public Observable<UpdateBean> hasNewversion(String t, String model, String android_id) {
        return observe(api.hasNewversion(t, model, android_id)
                .map(new Fun1Imp<UpdateBean>()));
    }

    public Observable<HomePageBean> getHomePageData(String model, String android_id) {
        return observe(api.getHomePageData(model, android_id)
                .map(new Fun1Imp<HomePageBean>()));
    }

}
