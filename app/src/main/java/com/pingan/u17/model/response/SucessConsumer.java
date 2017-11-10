package com.pingan.u17.model.response;

import com.pingan.u17.view.BaseView;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/6
 */

public  class SucessConsumer<T extends BaseResponse> implements Consumer<BaseResponse> {

    private  BaseView mView;

    @Override
    public void accept(@NonNull BaseResponse baseResponse) throws Exception {

    }
    /*@Override
    public void accept(@NonNull BaseResponse tBaseResponse) throws Exception {
        if (tBaseResponse ==null || tBaseResponse.code != 1 || !tBaseResponse.data.isSucess()) {
            mView.showErrorMsg("暂无网络");
        }
    }*/


   /* @Override
    public void accept(@NonNull P t) throws Exception {
        if (t ==null || t.code != 1 || !t.data.isSucess()) {
            mView.showErrorMsg("暂无网络");
        }
    }*/

}
