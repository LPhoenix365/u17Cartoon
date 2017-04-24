package com.pingan.u17.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pingan.u17.R;
import com.pingan.u17.base.BaseFragment;
import com.pingan.u17.adapter.RecommendAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：liupeng on 2017/2/24 09:56
 * Address：liupeng264@pingan.com.cn
 */
public class ChildRecommendFragment extends BaseFragment {
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_recommend, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvRecommend.setLayoutManager(new LinearLayoutManager(mActivity));
        rvRecommend.setAdapter(new RecommendAdapter());




    }
}
