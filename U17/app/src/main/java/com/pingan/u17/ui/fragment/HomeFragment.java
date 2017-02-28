package com.pingan.u17.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pingan.u17.R;
import com.pingan.u17.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：liupeng on 2017/2/24 09:54
 * Address：liupeng264@pingan.com.cn
 */
public class HomeFragment extends BaseFragment {
    /*@BindView(R.id.home_rollview)
    RollViewPager  homeRollview;*/
    @BindView(R.id.home_textview_icon)
    TextView       homeTextviewIcon;
    @BindView(R.id.home_textview_more)
    TextView       homeTextviewMore;
    @BindView(R.id.home_rl_recommend)
    RelativeLayout homeRlRecommend;
    @BindView(R.id.gv_local)
    GridView       gvLocal;
    @BindView(R.id.home_recyclerview)
    RecyclerView   homeRecyclerview;
    @BindView(R.id.home_concont_ll)
    LinearLayout   homeConcontLl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {

    }


}
