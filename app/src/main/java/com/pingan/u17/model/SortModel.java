package com.pingan.u17.model;

import com.pingan.u17.bean.SortPageBean;
import com.pingan.u17.net.rxImp.Fun1Imp;

import java.util.Map;

import rx.Observable;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public class SortModel extends BaseModel {


    public Observable<SortPageBean> getSortPageData(Map<String,String> map) {
        return observe(api.getSortPageData(map)
                .map(new Fun1Imp<SortPageBean>()));
    }

}
