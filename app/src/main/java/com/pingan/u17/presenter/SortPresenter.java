package com.pingan.u17.presenter;

import com.pingan.u17.bean.SortPageBean;
import com.pingan.u17.model.SortModel;
import com.pingan.u17.net.rxImp.Action1Imp;
import com.pingan.u17.view.SortView;

import java.util.Map;

import rx.functions.Action1;

/**
 * @author sanshu
 * @data 2017/7/2 16:20
 * @ToDo ${TODO}
 */

public class SortPresenter extends BasePresenter<SortView> {
    private SortView sortView;
    private SortModel sortModel;

    public SortPresenter(SortView sortView) {
        sortModel = new SortModel();
        this.sortView = sortView;
    }

    public void getSortPageData(Map<String,String> map){
        sortModel
                .getSortPageData(map)
                .subscribe(new Action1<SortPageBean>() {
                    @Override
                    public void call(SortPageBean sortPageBean) {
                        sortView.getSortPageData(sortPageBean);
                    }
                },new Action1Imp());
    }
}
