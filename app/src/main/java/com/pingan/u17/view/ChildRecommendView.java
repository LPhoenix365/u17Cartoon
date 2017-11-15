package com.pingan.u17.view;

import com.pingan.u17.model.response.HomePageResponse;

import io.reactivex.Observable;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public interface ChildRecommendView extends BaseView {
    void  getHomePageData(Observable<HomePageResponse> homePageBean);
   // void  hasNewVersion(Single<UpdateBean.ReturnDataBean.UpdateInfoBean> updateBean);
}
