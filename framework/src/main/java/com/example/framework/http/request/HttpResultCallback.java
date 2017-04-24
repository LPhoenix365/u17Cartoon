package com.example.framework.http.request;

import com.example.framework.http.abutil.ToastUtil;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/14
 */
public abstract class HttpResultCallback<T> {
    public abstract void onSuccess(T t,String serverTag);

    public void onFailure(TaskError error, String serverTag){
        if (error.getStatusCode() == 20000) {
            ToastUtil.showToast(error.getMsg());
        } else {
            ToastUtil.showToast("请求超时");
        }
    }
}
