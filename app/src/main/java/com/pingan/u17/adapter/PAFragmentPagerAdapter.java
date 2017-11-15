package com.pingan.u17.adapter;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pingan.u17.base.U17Application;

import java.util.ArrayList;
import java.util.List;


public class PAFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> tabFragments;
    private int[] tabTitleIds;//tab标题string的id
    private String[] tabTitles;

    public PAFragmentPagerAdapter(FragmentManager fm, @StringRes int[] tabTitleIds) {
        super(fm);
        this.tabTitleIds = tabTitleIds;
        tabFragments = new ArrayList<>(tabTitleIds.length);
    }

    public PAFragmentPagerAdapter(FragmentManager fm, String[] tabTitles) {
        super(fm);
        this. tabTitles = tabTitles;
        tabFragments = new ArrayList<>(tabTitles.length);
    }

    @Override
    public Fragment getItem(int position) {
        return tabFragments.get(position);
    }

    @Override
    public int getCount() {
        if(tabTitleIds != null) {
            return tabTitleIds.length;
        }
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(tabTitleIds != null) {
            return U17Application.getInstance().getString(tabTitleIds[position]);
        }
        return tabTitles[position];
    }



    public void addFragment(Fragment fragment) {
        tabFragments.add(fragment);
    }
}
