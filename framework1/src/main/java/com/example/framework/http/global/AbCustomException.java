package com.example.framework.http.global;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/28
 */

public class AbCustomException extends Exception {
    private static final long serialVersionUID = 11;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /** 异常消息. */
    private String msg = "";


    public AbCustomException() {
        super();
    }



    public AbCustomException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
