package com.pingan.u17.bean;

import com.pingan.u17.net.IResponse;

import java.io.Serializable;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/18
 */

public class BaseBean<T> implements Serializable, IResponse {


    private int    code;
    private String msg;
    private T      data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSucess() {
        return code == 1;
    }

}
