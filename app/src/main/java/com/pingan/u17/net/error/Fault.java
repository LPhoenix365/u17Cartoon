package com.pingan.u17.net.error;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */
public class Fault extends RuntimeException {
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    private String message;
    private int    errorCode;

    public Fault(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
