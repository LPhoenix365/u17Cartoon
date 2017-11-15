package com.pingan.u17.presenter;

import com.pingan.u17.bean.GuessLikeBean;
import com.pingan.u17.model.response.BaseResponse;
import com.pingan.u17.model.response.HttpSingleSubscriber;
import com.pingan.u17.util.RxUtils;
import com.pingan.u17.view.GuessLikeView;

import java.util.List;
import java.util.Map;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/6
 */

public class GuessLikePresenter extends BasePresenter<GuessLikeView> {

    private GuessLikeView mGuessLikeView;

    public void getCartoonGuessLike(Map<String, String> map) {
        mGuessLikeView = getView();
        commonServiceImp
                .getCartoonGuessLike(map)
                .compose(RxUtils.<BaseResponse<List<GuessLikeBean>>>defaultSchedulers_single())
                .subscribe(new HttpSingleSubscriber<List<GuessLikeBean>>() {
                    @Override
                    public void success(List<GuessLikeBean> returnDataBean) {
                        if (isViewAttached()) {
                            mGuessLikeView.getGuessLike(returnDataBean);
                        }
                    }

                    @Override
                    public void error(Throwable e) {
                        if (isViewAttached()) {
                            mGuessLikeView.showErrorMsg(e.getMessage());
                        }
                    }
                });
    }
}
