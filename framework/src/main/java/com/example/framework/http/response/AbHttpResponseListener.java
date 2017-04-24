package com.example.framework.http.response;

import android.os.Handler;
import android.os.Message;

import com.example.framework.http.request.AbHttpClient;


/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/14
 */

public abstract class AbHttpResponseListener {

    /**
     * The handler.
     */
    private Handler mHandler;

    private Boolean mIsSend = true;

    public String moduleFlag = "";//模块标识

    /**
     * 构造.
     */
    public AbHttpResponseListener() {
        super();
    }

    /**
     * 描述：获取数据开始,初始化Loading（圈圈）
     */
    public abstract void initProgressDialog();


    /**
     * 完成后调用，失败，成功，调用.
     */
    public abstract void dismissProgressDialog();

    /**
     * 描述：获取数据开始,初始化Loading（状态小人）
     */
    public abstract void initLoadingDialog();

    /**
     * 描述：获取数据开始.
     */
    public abstract void onStart();


    /**
     * 完成后调用，失败，成功，调用.
     */
    public abstract void onFinish();

    /**
     * 重试.
     */
    public void onRetry() {
    }

    /**
     * 描述：失败，调用.
     *
     * @param statusCode the status code
     * @param content    the content
     * @param error      the error
     */
    public abstract void onFailure(int statusCode, String content, Throwable error);

    /**
     * 进度.
     *
     * @param bytesWritten the bytes written
     * @param totalSize    the total size
     */
    public void onProgress(int bytesWritten, int totalSize) {
    }

    /**
     * 开始消息.
     */
    public void sendStartMessage() {
        sendMessage(obtainMessage(AbHttpClient.START_MESSAGE, null));
    }

    /**
     * 完成消息.
     */
    public void sendFinishMessage() {
        sendMessage(obtainMessage(AbHttpClient.FINISH_MESSAGE, null));
    }

    /**
     * 进度消息.
     *
     * @param bytesWritten the bytes written
     * @param totalSize    the total size
     */
    public void sendProgressMessage(int bytesWritten, int totalSize) {
        sendMessage(obtainMessage(AbHttpClient.PROGRESS_MESSAGE, new Object[]{bytesWritten, totalSize}));
    }

    /**
     * 失败消息.
     *
     * @param statusCode the status code
     * @param content    the content
     * @param error      the error
     */
    public void sendFailureMessage(int statusCode, String content, Throwable error) {
        sendMessage(obtainMessage(AbHttpClient.FAILURE_MESSAGE, new Object[]{statusCode, content, error}));
    }

    /**
     * 重试消息.
     */
    public void sendRetryMessage() {
        sendMessage(obtainMessage(AbHttpClient.RETRY_MESSAGE, null));
    }

    /**
     * 发送消息.
     *
     * @param msg the msg
     */
    public void sendMessage(Message msg) {

        if (msg != null && mIsSend) {
            msg.sendToTarget();
        }
    }

    /**
     * 构造Message.
     *
     * @param responseMessage the response message
     * @param response        the response
     * @return the message
     */
    protected Message obtainMessage(int responseMessage, Object response) {
        Message msg;
        if (mHandler != null) {
            msg = mHandler.obtainMessage(responseMessage, response);
        } else {
            msg = Message.obtain();
            if (msg != null) {
                msg.what = responseMessage;
                msg.obj = response;
            }
        }
        return msg;
    }

    /**
     * Gets the handler.
     *
     * @return the handler
     */
    public Handler getHandler() {
        return mHandler;
    }

    /**
     * 描述：设置Handler.
     *
     * @param handler the new handler
     */
    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }

    public void stopSendMessage() {
        mIsSend = false;
    }
}
