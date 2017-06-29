package com.pingan.u17.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.pingan.u17.net.RestApi;

/**
 * Author：liupeng on 2017/2/24 09:40
 * Address：liupeng264@pingan.com.cn
 */


public class BaseFragment extends Fragment {

    public FragmentActivity mActivity;
    protected RestApi         api;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //防止后面获取activity失败 报空
        mActivity = getActivity();
        api = U17Application.getInstance().getHttpClient().getApiService();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //是否有保存的实例
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
