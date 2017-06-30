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


public  class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    public FragmentActivity mActivity;
    protected RestApi         api;
    protected T mPresenter;


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

        //允许为空不是每个都要实现MVP
        if (createPresenter() != null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
        }
        return rootView;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();

    }

    protected  T createPresenter(){
        return null;
    }
    protected  int createViewLayoutId(){
        return 0;
    }

}
