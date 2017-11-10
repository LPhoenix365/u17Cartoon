package com.pingan.u17.model.response;

import com.pingan.u17.util.U17Log;

import java.util.concurrent.CancellationException;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author liupeng502
 * @data 2017/11/8
 */
public abstract class HttpSingleSubscriber<T> implements SingleObserver<BaseResponse<T>> {
    //出错提示
    private static final String MSG_NETWORK_ERROR = "网络错误";
    private static final String MSG_NETWORK_CONNECTION_ERROR = "网络连接不可用，请检查或稍后重试";
    private static final String MSG_UNKNOWN_ERROR = "Ops，好像出错了~";
    private static final String MSG_TIME_OUT = "网络请求超时";
    private static final String MSG_SERVER_ERROR = "服务器错误";

    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onSuccess(@NonNull BaseResponse<T> baseResponse) {
        if (baseResponse != null && baseResponse.isNetSucess()) {
            if (baseResponse.data != null && baseResponse.data.isSucess()) {
                U17Log.i("okhttp", "response:" + baseResponse.data.toString());
                success(baseResponse.data.returnData);
            } else {
                error(new Throwable(MSG_TIME_OUT));
            }
        } else {
            error(new Throwable(MSG_SERVER_ERROR));
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e != null) {
            //处理RxLife取消订阅的问题,这实际上并不是一个真正的错误
            //问题链接:https://github.com/trello/RxLifecycle/tree/2.x#unsubscription
            if (!(e instanceof CancellationException)) {
                e.printStackTrace();
                if (e.getMessage() == null) {
                    error(new Throwable(e.toString()));
                } else {
                    error(new Throwable(e.getMessage()));
                }
            }
        } else {
            error(new Exception("null message"));
        }
    }

    public abstract void success(T t);

    public abstract void error(Throwable e);
}
