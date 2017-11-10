package com.pingan.u17.presenter;

import android.content.Context;

import com.example.framework.http.abutil.AbLogUtil;
import com.pingan.u17.bean.HomePageBean;
import com.pingan.u17.bean.UpdateBean;
import com.pingan.u17.model.ChildRecommendModel;
import com.pingan.u17.view.ChildRecommendView;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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


    public void getHomePageData(Map<String, String> map) {
        childRecommendView = getView();
        if (childRecommendView != null) {
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
        }

    }

    public void hasNewversion(Map<String, String> map) {
        childRecommendView = getView();
        if (childRecommendView != null) {
            childRecommendModel
                    .hasNewversion(map)
                    .subscribe(
                            new Consumer<UpdateBean>() {
                                @Override
                                public void accept(@NonNull UpdateBean updateBean) throws Exception {
                                    UpdateBean data = updateBean.getData();
                                    UpdateBean.ReturnDataBean.UpdateInfoBean updateInfo = data.getReturnData().getUpdateInfo();
                                    AbLogUtil.d(TAG, "updateInfo=" + updateInfo);
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


}
