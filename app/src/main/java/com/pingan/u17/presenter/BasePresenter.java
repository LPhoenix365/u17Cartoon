package com.pingan.u17.presenter;

import com.example.framework.http.abutil.AbLogUtil;
import com.pingan.u17.model.BaseModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */
public class BasePresenter<V> {

    protected Reference<V> mViewRef;

    public void attachView(V view){
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView(){
        return mViewRef.get();
    }

    public boolean isViewAttached(){
        return mViewRef != null&& mViewRef.get()!=null;
    }

    public void detachView(){
        if(mViewRef!=null){
            mViewRef.clear();
            mViewRef = null;
        }
    }


    protected void setBaseModel(BaseModel baseM) {

    }


    public   Consumer<Throwable> disposeFailureInfo(Throwable t) {
        return new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                AbLogUtil.d("tag","throwable="+throwable);
            }
        };


        /*throwable -> {
            if (t.toString().contains("GaiException") || t.toString().contains("SocketTimeoutException") ||
                    t.toString().contains("UnknownHostException")) {
                //ToastUtil.showShort("网络问题");
            } else if (t.toString().contains("API没有")) {
                *//*OrmLite.getInstance()
                        .delete(new WhereBuilder(CityORM.class).where("name=?", Util.replaceInfo(t.getMessage())));
                ToastUtil.showShort("错误: " + t.getMessage());*//*
            }
            //PLog.w(t.getMessage());
        };*/
    }
}
