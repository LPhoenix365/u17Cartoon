package com.pingan.u17.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/1
 */
public class HomePagerAdapter extends FragmentPagerAdapter {


    private  List<Fragment> mListFragments;
    private List<String> mTitles;

    /**
     * @param
     * @param titles
     */
    public HomePagerAdapter(FragmentManager manager, List<Fragment> listFragments, List<String> titles) {
        super(manager);
        mListFragments = listFragments;
        mTitles = titles;
    }

    @Override
    public int getCount() {
        return mListFragments == null ? 0 : mListFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragments.get(position);
    }
}
