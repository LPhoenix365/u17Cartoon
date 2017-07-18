package com.pingan.u17.presenter;

import com.example.framework.http.abutil.AbLogUtil;
import com.pingan.u17.bean.HomePageBean;
import com.pingan.u17.bean.UpdateBean;
import com.pingan.u17.model.ChildRecommendModel;
import com.pingan.u17.view.ChildRecommendView;

import java.lang.ref.WeakReference;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public class ChildRecommendPresenter extends BasePresenter<ChildRecommendView> {
    private ChildRecommendView childRecommendView;
    public static final String TAG = ChildRecommendPresenter.class.getSimpleName();

    private ChildRecommendModel childRecommendModel;

    public ChildRecommendPresenter(WeakReference<ChildRecommendView> childRecommendView) {
        childRecommendModel = new ChildRecommendModel();
        this.childRecommendView = childRecommendView.get();
    }



/*
    public void hasNewversion(String t, String model, String android_id) {
        childRecommendModel
                .hasNewversion(t, model, android_id)
                .subscribe(new Action1<UpdateBean>() {
                    @Override
                    public void call(UpdateBean updateBean) {
                        childRecommendView.hasNewVersion(updateBean);
                    }
                }, new Action1Imp());
    }*/

    public void getHomePageData(Map<String, String> map) {
        childRecommendModel
                .getHomePageData(map)
                .subscribe(new Consumer<HomePageBean>() {
                    @Override
                    public void accept(HomePageBean homePageBean) throws Exception {
                        if (homePageBean != null && homePageBean.getCode() == 1) {
                            HomePageBean data = homePageBean.getData();
                            HomePageBean.ReturnDataBean dataBean = data.getReturnData();

                            childRecommendView.getHomePageData2(Observable.just(dataBean));
                            childRecommendView.showErrorMsg("");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        childRecommendView.showErrorMsg("");
                    }
                });
    }

    public void hasNewversion(Map<String,String> map){
        childRecommendModel
                .hasNewversion(map)
                .subscribe(new Consumer<UpdateBean>() {
                               @Override
                               public void accept(@NonNull UpdateBean updateBean) throws Exception {
                                   UpdateBean data = updateBean.getData();
                                   UpdateBean.ReturnDataBean.UpdateInfoBean updateInfo = data.getReturnData().getUpdateInfo();
                                   AbLogUtil.d(TAG,"updateInfo="+updateInfo);
                                   childRecommendView.hasNewVersion(Single.just(updateInfo));
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(@NonNull Throwable throwable) throws Exception {
                                   childRecommendView.showErrorMsg("");
                               }
                           }
                );


    }
}
