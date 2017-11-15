package com.pingan.u17.presenter;

import android.content.Context;

import com.pingan.u17.model.ChildRecommendModel;
import com.pingan.u17.model.response.HomePageResponse;
import com.pingan.u17.model.response.HttpSingleSubscriber;
import com.pingan.u17.util.RxUtils;
import com.pingan.u17.view.ChildRecommendView;

import java.util.Map;

import io.reactivex.Observable;

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

    public ChildRecommendPresenter(Context context) {
        childRecommendModel = new ChildRecommendModel();
    }


   /*  if (childRecommendView != null) {
        childRecommendModel
                .getHomePageData(map)
                .flatMap(new Function<HomePageBean, ObservableSource<HomePageBean.ReturnDataBean>>() {
                    @Override
                    public ObservableSource<HomePageBean.ReturnDataBean> apply(@NonNull HomePageBean homePageBean) throws Exception {
                        if (homePageBean.isSucess() && homePageBean.getData().getReturnData() != null) {
                            return Observable.just(homePageBean.getData().getReturnData());
                        }
                        return Observable.error(new RuntimeException("解析错误"));
                    }
                })
                .subscribe(new Consumer<HomePageBean.ReturnDataBean>() {
                    @Override
                    public void accept(@NonNull HomePageBean.ReturnDataBean returnDataBean) throws Exception {
                        childRecommendView.getHomePageData(Observable.just(returnDataBean));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        childRecommendView.getHomePageData(null);
                        childRecommendView.showErrorMsg("");
                    }
                });
    }*/


    public void getHomePageData(Map<String, String> map) {
        childRecommendView = getView();
        commonServiceImp
                .getHomePageData(map)
                .compose(RxUtils.defaultSchedulers_single())
                .subscribe(new HttpSingleSubscriber<HomePageResponse>() {
                    @Override
                    public void success(HomePageResponse homePageResponse) {
                        if (childRecommendView != null) {
                            childRecommendView.getHomePageData(Observable.just(homePageResponse));
                        }
                    }

                    @Override
                    public void error(Throwable e) {
                        if (childRecommendView != null) {
                            childRecommendView.showErrorMsg(e.getMessage());
                        }
                    }
                });


    }



}
