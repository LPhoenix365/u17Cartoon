package com.pingan.u17.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pingan.u17.net.RestApi;
import com.pingan.u17.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * Author：liupeng on 2017/2/24 09:40
 * Address：liupeng264@pingan.com.cn
 */


public  class BaseFragment<V, P extends BasePresenter<V>> extends Fragment {

    public    FragmentActivity mActivity;
    protected RestApi          api;
    protected P                mPresenter;
    protected LayoutInflater   mInflater;
    protected boolean          hasLoadOnce;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //防止后面获取activity失败 报空
        mActivity = getActivity();
        api = U17Application.getInstance().getHttpClient().getApiService();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(createViewLayoutId(),container,false);
        ButterKnife.bind(this,rootView);
        mInflater = LayoutInflater.from(mActivity);
        //允许为空不是每个都要实现MVP
        if (createPresenter() != null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
        }
        return rootView;
    }

    //fragment 懒加载 适合tablayout与fragment使用
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if(getUserVisibleHint()) {//
            hasLoadOnce = true;
            doLazyRequest();//ViewPager的第1个Fragment会走这里
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(!hasLoadOnce && isVisible() && isVisibleToUser) {
            hasLoadOnce = true;
            doLazyRequest();//ViewPager的第2,3..个Fragment会走这里
        }
        super.setUserVisibleHint(isVisibleToUser);
    }






    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();

    }

    protected P createPresenter(){
        return null;
    }
    protected  int createViewLayoutId(){
        return 0;
    }

    protected void doLazyRequest() {

    }

}
