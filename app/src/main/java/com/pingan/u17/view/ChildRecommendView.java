package com.pingan.u17.view;

import com.pingan.u17.bean.HomePageBean;
import com.pingan.u17.bean.UpdateBean;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public interface ChildRecommendView extends BaseView {
    void  getHomePageData2(Observable<HomePageBean.ReturnDataBean> homePageBean);
    void  hasNewVersion(Single<UpdateBean.ReturnDataBean.UpdateInfoBean> updateBean);
}
