package com.pingan.u17.net.rxImp;

import com.pingan.u17.bean.BaseBean;
import com.pingan.u17.net.error.Fault;

import rx.functions.Func1;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public class Fun1Imp<T> implements Func1<BaseBean<T>,T>{
    @Override
    public T call(BaseBean tBaseBean) {
        if (!tBaseBean.isSucess()) {
            //抛异常 必须catch吗
           throw new Fault(tBaseBean.getCode(),tBaseBean.getMsg());
        }
        return (T) tBaseBean.getData();
    }
}
