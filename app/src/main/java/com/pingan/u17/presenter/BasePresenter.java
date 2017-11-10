package com.pingan.u17.presenter;

import com.pingan.u17.model.BaseModel;
import com.pingan.u17.net.CommonServiceImp;
import com.pingan.u17.net.ServiceFactory;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */
public class BasePresenter<V> {

    protected Reference<V> mViewRef;
    protected CommonServiceImp commonServiceImp;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public BasePresenter() {
        commonServiceImp = ServiceFactory.getInstance().createService(CommonServiceImp.class);
    }

    protected void setBaseModel(BaseModel baseM) {

    }
}
