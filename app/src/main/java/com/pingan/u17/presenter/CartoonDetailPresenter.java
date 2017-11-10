package com.pingan.u17.presenter;

import com.pingan.u17.model.response.CartoonDetailRealtimeResponse;
import com.pingan.u17.model.response.CartoonDetailResponse;
import com.pingan.u17.model.response.HttpSingleSubscriber;
import com.pingan.u17.util.RxUtils;
import com.pingan.u17.view.CartoonDetailView;

import java.util.Map;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/6
 */

public class CartoonDetailPresenter extends BasePresenter<CartoonDetailView> {

    private CartoonDetailView cartoonDetailView;

    public void getCartoonDetailData(Map<String, String> map) {
        cartoonDetailView = getView();
        commonServiceImp
                .getCartoonDetailData(map)
                .compose(RxUtils.<CartoonDetailResponse>defaultSchedulers_single())
                .subscribe(new HttpSingleSubscriber<CartoonDetailResponse>() {
                    @Override
                    public void success(CartoonDetailResponse returnDataBean) {
                        if (isViewAttached()) {
                            cartoonDetailView.getCartoonDetail(returnDataBean);
                        }
                    }

                    @Override
                    public void error(Throwable e) {
                        if (isViewAttached()) {
                            cartoonDetailView.showErrorMsg(e.getMessage());
                        }
                    }
                });
    }


    public void getCartoonDetailRealtime(Map<String, String> map) {
        cartoonDetailView = getView();
        commonServiceImp
                .getCartoonDetailRealtime(map)
                .compose(RxUtils.<CartoonDetailRealtimeResponse>defaultSchedulers_single())
                .subscribe(new HttpSingleSubscriber<CartoonDetailRealtimeResponse>() {
                    @Override
                    public void success(CartoonDetailRealtimeResponse returnDataBean) {
                        if (isViewAttached()) {
                            cartoonDetailView.getCartoonDetailRealtime(returnDataBean);
                        }
                    }

                    @Override
                    public void error(Throwable e) {
                        if (isViewAttached()) {
                            cartoonDetailView.showErrorMsg(e.getMessage());
                        }
                    }
                });
    }
}
