package com.pingan.u17.bean;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/28
 */

public class DataBean<T> extends BaseBean {

    private int    stateCode;
    private String message;
    private T      returnData;

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getReturnData() {
        return returnData;
    }

    public void setReturnData(T returnData) {
        this.returnData = returnData;
    }
}
