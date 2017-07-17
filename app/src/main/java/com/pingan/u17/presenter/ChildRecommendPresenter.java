package com.pingan.u17.presenter;

import com.pingan.u17.bean.HomePageBean;
import com.pingan.u17.model.ChildRecommendModel;
import com.pingan.u17.view.ChildRecommendView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

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

    public void getHomePageData(Map<String,String> map) {
          childRecommendModel
                .getHomePageData(map)
                .flatMap(new Function<HomePageBean, Observable<HomePageBean.ReturnDataBean>>() {
                    @Override
                    public Observable<HomePageBean.ReturnDataBean> apply(HomePageBean homePageBean) throws Exception {
                        HomePageBean data = homePageBean.getData();
                        HomePageBean.ReturnDataBean dataBean = data.getReturnData();
                       /* HomePageBean.ReturnDataBean returnData = null;
                        returnData = homePageBean.getReturnData();*/
                        return Observable.just(dataBean);
                    }
                })
                .flatMap(new Function<HomePageBean.ReturnDataBean, ObservableSource<HomePageBean.ReturnDataBean.ComicListsBean>>() {
                    @Override
                    public ObservableSource<HomePageBean.ReturnDataBean.ComicListsBean> apply(HomePageBean.ReturnDataBean returnDataBean) throws Exception {
                        List<HomePageBean.ReturnDataBean.ComicListsBean> comicLists = returnDataBean.getComicLists();
                        List<HomePageBean.ReturnDataBean.GalleryItemsBean> galleryItems = returnDataBean.getGalleryItems();
                        //List<HomePageBean.ReturnDataBean.GalleryItemsBean> galleryItems = returnDataBean.getGalleryItems();
                        return Observable.fromIterable(comicLists);
                    }
                })
                .take(10);

    }
}
