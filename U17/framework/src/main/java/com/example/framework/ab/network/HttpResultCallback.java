package com.example.framework.ab.network;

import com.paic.mhis.siapp.R;
import com.paic.mhis.siapp.view.ToastUtil;

import static com.paic.mhis.siapp.utils.UIUtils.getResources;

/**
 * @Description 网络请求回调接口
 * @Author chenyongjian949
 * @Email chenyongjian949@pingan.com.cn
 * @Date 16/7/12
 * @Version 2.5.1
 * modify by luxiao418 修改为抽象,请求失败情况统一处理
 */
public abstract class HttpResultCallback<T> {

    public abstract void onSuccess(T t,String serverTag);

    public void onFailure(TaskError error, String serverTag){
        if (error.getStatusCode() == 20000) {
            ToastUtil.showToast(error.getMsg());
        } else {
            ToastUtil.showToast(getResources().getString(R.string.timeout));
        }
    }
}
