package com.pingan.u17.presenter;

import com.pingan.u17.bean.HomePageBean;
import com.pingan.u17.bean.UpdateBean;
import com.pingan.u17.model.ChildRecommendModel;
import com.pingan.u17.net.rxImp.Action1Imp;
import com.pingan.u17.view.ChildRecommendView;

import java.util.Map;

import rx.functions.Action1;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public class ChildRecommendPresenter extends BasePresenter<ChildRecommendView> {
    private ChildRecommendView childRecommendView;

    private ChildRecommendModel childRecommendModel;

    public ChildRecommendPresenter(ChildRecommendView childRecommendView) {
        childRecommendModel = new ChildRecommendModel();
        this.childRecommendView = childRecommendView;
    }




    public void hasNewversion(String t, String model, String android_id) {
        childRecommendModel
                .hasNewversion(t, model, android_id)
                .subscribe(new Action1<UpdateBean>() {
                    @Override
                    public void call(UpdateBean updateBean) {
                        childRecommendView.hasNewVersion(updateBean);
                    }
                }, new Action1Imp());
    }

    public void getHomePageData(Map<String,String> map) {
        childRecommendModel
                .getHomePageData(map)
                .subscribe(new Action1<HomePageBean>() {
                    @Override
                    public void call(HomePageBean homePageBean) {
                        childRecommendView.getHomePageData(homePageBean);
                    }
                }, new Action1Imp());
    }
}
