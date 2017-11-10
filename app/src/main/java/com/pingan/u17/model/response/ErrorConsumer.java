package com.pingan.u17.model.response;

import com.pingan.u17.presenter.BasePresenter;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/6
 */

public  class ErrorConsumer implements Consumer<Throwable> {
    //HTTP的状态码
    private static final int HTTP_BAD_REQUEST = 400;
    private static final int HTTP_FORBIDDEN = 403;
    private static final int HTTP_NOT_FOUND = 404;
    private static final int HTTP_TIMEOUT = 408;
    private static final int HTTP_INTERNAL_SERVER_ERROR = 500;
    //出错提示
    private static final String MSG_NETWORK_ERROR = "网络错误";
    private static final String MSG_NETWORK_CONNECTION_ERROR = "网络连接不可用，请检查或稍后重试";
    private static final String MSG_UNKNOWN_ERROR = "Ops，好像出错了~";
    private static final String MSG_TIME_OUT = "网络请求超时";
    private static final String MSG_SERVER_ERROR = "服务器错误";
    private BasePresenter mPresenter;

    public  ErrorConsumer(BasePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void accept(@NonNull Throwable throwable) throws Exception {
        resolveException(throwable);
    }


    private void resolveException(Throwable e) {
        if (e instanceof Throwable) {

        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case HTTP_BAD_REQUEST:
                case HTTP_FORBIDDEN:
                case HTTP_NOT_FOUND:
                case HTTP_INTERNAL_SERVER_ERROR:
                    error(MSG_SERVER_ERROR);
                    break;
                case HTTP_TIMEOUT:
                    error(MSG_TIME_OUT);
                    break;
                default:
                    error(MSG_NETWORK_ERROR);
                    break;
            }
        } else if (e instanceof SocketTimeoutException) {
            error(MSG_TIME_OUT);
        } else if (e instanceof ConnectException) {
            error(MSG_NETWORK_ERROR);
        } else if (e instanceof UnknownHostException) {
            error(MSG_NETWORK_CONNECTION_ERROR);
        } else if (e instanceof SocketException) {
            error(MSG_SERVER_ERROR);
        } else {
            error(MSG_UNKNOWN_ERROR);
        }
    }

    private void error(String msg) {
        if (mPresenter.isViewAttached()) {
            onError(msg);
        }
    }
    public void onError(String msg){};
}
