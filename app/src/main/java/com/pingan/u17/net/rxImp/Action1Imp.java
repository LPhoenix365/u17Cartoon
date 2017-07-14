package com.pingan.u17.net.rxImp;

import com.example.framework.http.abutil.AbLogUtil;
import com.pingan.u17.net.error.Fault;

import rx.functions.Action1;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public class Action1Imp implements Action1<Throwable> {
    public static final String TAG = Action1Imp.class.getSimpleName();

    @Override
    public void call(Throwable throwable) {
        if (throwable instanceof Fault) {
            Fault fault = (Fault) throwable;
            AbLogUtil.i(TAG,"fault.getErrorCode()="+fault.getErrorCode()+"fault.getMessage()="+fault.getMessage());
            if (fault.getErrorCode() == 404) {
                //错误处理
            } else if (fault.getErrorCode() == 500) {
                //错误处理
            } else if (fault.getErrorCode() == 501) {
                //错误处理
            }
        }
    }
}
