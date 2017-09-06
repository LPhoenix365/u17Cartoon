package com.pingan.u17.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.pingan.u17.R;
import com.pingan.u17.base.BaseFragment;
import com.pingan.u17.bean.SortPageBean;
import com.pingan.u17.presenter.SortPresenter;
import com.pingan.u17.view.SortView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author：liupeng on 2017/2/24 09:55
 * Address：liupeng264@pingan.com.cn
 */
public class SortFragment extends BaseFragment<SortView, SortPresenter> implements SortView {
    public static final String TAG = SortFragment.class.getSimpleName();
    @BindView(R.id.calendar)
    CalendarView calendar;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void doLazyRequest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "b3e26427e8fdbc7642899d2f79349691055ca77068eaec6bb906c5d9583200" +
                "7bcc021e99063bd5d20c67f45ec38bf50e5d5be1d2e29b490eec1278753f9a59d76cc51277320d249a3720f7919c48bf2c32de0f9d32045b70c4f7842dde599c18a8dd80498a3cb3d4f5fbbbdfcd0c5625ad9bedb63caf14943fe52f75d0847fe786150d1da107706f42ff8db278b46423730e4a679626c192");
        map.put("model", "Lenovo+K920");
        map.put("android_id", "4314d9d0e238ef04");
        map.put("version", "2");
        //mPresenter.getSortPageData(map);
    }

    @Override
    protected SortPresenter createPresenter() {
        return new SortPresenter(this);
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
        unbinder.unbind();
    }
}
