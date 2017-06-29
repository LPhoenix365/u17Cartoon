package com.pingan.u17.bean;

import java.io.Serializable;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/18
 */

public class BaseBean implements Serializable {


    private int    code;
    private String msg;
    private DataBean   data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


}
