package com.pingan.u17.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.framework.http.abutil.AbLogUtil;
import com.pingan.u17.R;
import com.pingan.u17.base.BaseFragment;
import com.pingan.u17.bean.SortPageBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author：liupeng on 2017/2/24 09:55
 * Address：liupeng264@pingan.com.cn
 */
public class SortFragment extends BaseFragment {
    public static final String TAG = SortFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HashMap<String, String> map = new HashMap<>();
        map.put("key","b3e26427e8fdbc7642899d2f79349691055ca77068eaec6bb906c5d9583200" +
                "7bcc021e99063bd5d20c67f45ec38bf50e5d5be1d2e29b490eec1278753f9a59d76cc51277320d249a3720f7919c48bf2c32de0f9d32045b70c4f7842dde599c18a8dd80498a3cb3d4f5fbbbdfcd0c5625ad9bedb63caf14943fe52f75d0847fe786150d1da107706f42ff8db278b46423730e4a679626c192");
        map.put("model","Lenovo+K920");
        map.put("android_id","4314d9d0e238ef04");
        map.put("version","2");
        api.getSortPageData(map).enqueue(new Callback<SortPageBean>() {


            @Override
            public void onResponse(Call<SortPageBean> call, Response<SortPageBean> response) {
                AbLogUtil.d(TAG,"response="+response);
            }

            @Override
            public void onFailure(Call<SortPageBean> call, Throwable t) {
                AbLogUtil.d(TAG,"t="+t);

            }
        });
    }
}