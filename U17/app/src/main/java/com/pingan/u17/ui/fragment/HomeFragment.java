package com.pingan.u17.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pingan.u17.R;
import com.pingan.u17.base.BaseFragment;
import com.pingan.u17.ui.adapter.HomePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：liupeng on 2017/2/24 09:54
 * Address：liupeng264@pingan.com.cn
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.tab_home)
    TabLayout tabHome;
    @BindView(R.id.vp_home)
    ViewPager vpHome;
    private final List<String> mTitles =new ArrayList<>(4);


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
        mTitles.add("推荐");
        mTitles.add("VIP");
        mTitles.add("订阅");
        mTitles.add("排行");
        tabHome.addTab(tabHome.newTab().setText(mTitles.get(0)));
        tabHome.addTab(tabHome.newTab().setText(mTitles.get(1)));
        tabHome.addTab(tabHome.newTab().setText(mTitles.get(2)));
        tabHome.addTab(tabHome.newTab().setText(mTitles.get(3)));

        ChildRecommendFragment recommendFragment = new ChildRecommendFragment();
        ChildVipFragment vipFragment = new ChildVipFragment();
        ChildSubscibeFragment subscibeFragment = new ChildSubscibeFragment();
        ChildRankFragment rankFragment = new ChildRankFragment();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(recommendFragment);
        fragments.add(vipFragment);
        fragments.add(subscibeFragment);
        fragments.add(rankFragment);
        FragmentManager manager = getFragmentManager();
        HomePagerAdapter adapter = new HomePagerAdapter(manager, fragments, mTitles);
        vpHome.setAdapter(adapter);
        tabHome.setupWithViewPager(vpHome);
        initData();
    }

    private void initData() {
    }



}
