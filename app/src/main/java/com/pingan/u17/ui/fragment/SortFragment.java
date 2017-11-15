package com.pingan.u17.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pingan.u17.R;
import com.pingan.u17.base.BaseFragment;
import com.pingan.u17.bean.SortPageBean;
import com.pingan.u17.presenter.SortPresenter;
import com.pingan.u17.view.SortView;

/**
 *
 * @author  liupeng502
 * @data    2017/11/13
 */
public class SortFragment extends BaseFragment<SortView, SortPresenter> implements SortView {
    public static final String TAG = SortFragment.class.getSimpleName();




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void doLazyRequest() {

    }

    @Override
    protected SortPresenter createPresenter() {
        return new SortPresenter();
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void getSortPageData(SortPageBean sortPageBean) {

    }


    @Override
    public void showErrorMsg(String errormsg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
