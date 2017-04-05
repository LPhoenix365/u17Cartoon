package com.example.framework.ab.network;

/**
 * @Description 异常处理
 * @Author chenyongjian949
 * @Email chenyongjian949@pingan.com.cn
 * @Date 16/7/12
 * @Version 2.5.1
 */
public class TaskError {
    int parserError = 1001;
    int statusCode;
    String msg;
    Throwable error;
    public TaskError(){

    }
    public TaskError(String msg) {
        this.statusCode = parserError;
        this.msg = msg;
    }
    public TaskError(int statusCode,String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
    public TaskError(int statusCode, String msg, Throwable error) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.error = error;
    }

    @Override
    public String toString() {
        return "TaskError{" +
                "statusCode=" + statusCode +
                ", msg='" + msg + '\'' +
                ", error=" + error +
                '}';
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
