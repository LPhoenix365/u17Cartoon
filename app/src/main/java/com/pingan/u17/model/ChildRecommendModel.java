package com.pingan.u17.model;

import com.pingan.u17.bean.HomePageBean;
import com.pingan.u17.bean.UpdateBean;
import com.pingan.u17.util.RxUtils;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
   /* public Observable<UpdateBean> hasNewversion(String t, String model, String android_id) {
        return observe(api.hasNewversion(t, model, android_id)
                .map(new Fun1Imp<UpdateBean>()));
    }

    public Observable<HomePageBean> getHomePageData(Map<String,String> map) {
        return observe(api.getHomePageData(map)
                .map(new Fun1Imp<HomePageBean>()));
    }*/

   public Observable<HomePageBean> getHomePageData(Map<String,String> map){
       return api
               .getHomePageData(map)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread());
   }

    public Single<UpdateBean> hasNewversion(Map<String,String> map){
        return api
                .hasNewversion(map)
                .compose(RxUtils.<UpdateBean>defaultSchedulers_single());
    }

}
