package com.example.framework.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.framework.http.abutil.AbLogUtil;
import com.example.framework.http.global.AbAppConfig;
import com.example.framework.http.global.AbAppException;
import com.example.framework.http.global.AbCustomException;
import com.example.framework.http.request.AbHttpClient;
import com.example.framework.http.response.AbBinaryHttpResponseListener;
import com.example.framework.http.response.AbFileHttpResponseListener;
import com.example.framework.http.response.AbHttpResponseListener;
import com.example.framework.http.response.AbStringHttpResponseListener;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/14
 */
public class ResponderHandler extends Handler {

    private Context                mContext;
    private AbHttpResponseListener mResultResponseListener;

    public ResponderHandler(Context context, AbHttpResponseListener resultResponseListener) {
        mContext = context;
        mResultResponseListener = resultResponseListener;
    }

    /**
     * 响应数据.
     */
    private Object[] response;

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case AbHttpClient.SUCCESS_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null) {
                    {
                        if (mResultResponseListener instanceof AbStringHttpResponseListener) {
                            if (response.length >= 2) {
                                String json = (String) response[1];
                                try {
                                    JSONObject jsonObject = new JSONObject(json);
                                    String code = jsonObject.optString("code");
                                    if (Integer.valueOf(code) == 1) {
                                        String data = jsonObject.optString("data");
                                        JSONObject object = new JSONObject(data);
                                        String stateCode = object.optString("stateCode");
                                        if (Integer.valueOf(stateCode) == 1) {
                                            String returnData = object.optString("returnData");
                                        ((AbStringHttpResponseListener) mResultResponseListener).onSuccess((Integer) response[0], returnData);
                                        }
                                    }else{
                                        mResultResponseListener.onFailure((Integer) response[0], (String)response[1],new AbCustomException("解析异常"));
                                    }
                                } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            } else {
                                AbLogUtil.e(mContext, "SUCCESS_MESSAGE " + AbAppConfig.MISSING_PARAMETERS);
                            }

                        } else if (mResultResponseListener instanceof AbBinaryHttpResponseListener) {
                            if (response.length >= 2) {
                                ((AbBinaryHttpResponseListener) mResultResponseListener).onSuccess((Integer) response[0],
                                        (byte[]) response[1]);
                            } else {
                                AbLogUtil.e(mContext, "SUCCESS_MESSAGE " + AbAppConfig.MISSING_PARAMETERS);
                            }
                        } else if (mResultResponseListener instanceof AbFileHttpResponseListener) {

                            if (response.length >= 1) {
                                AbFileHttpResponseListener mAbFileHttpResponseListener = ((AbFileHttpResponseListener) mResultResponseListener);
                                mAbFileHttpResponseListener.onSuccess((Integer) response[0],
                                        mAbFileHttpResponseListener.getFile());
                            } else {
                                AbLogUtil.e(mContext, "SUCCESS_MESSAGE " + AbAppConfig.MISSING_PARAMETERS);
                            }

                        }
                    }
                }
                break;
            case AbHttpClient.FAILURE_MESSAGE:
                response = (Object[]) msg.obj;
                AbLogUtil.e("TAG", "response:" + response.toString());
                if (response != null && response.length >= 3) {
                    // 异常转换为可提示的
                    int stateCode = (Integer) response[0];
                    AbLogUtil.e("stateCode", "stateCode:" + stateCode);
                    if (stateCode == HttpStatus.SC_UNAUTHORIZED) {  //401:手机号被挤掉
                        return;
                    } else if (stateCode == 430) {//社保卡被别的手机绑定
                        return;
                    }
                    AbAppException exception = new AbAppException((Exception) response[2]);
                    mResultResponseListener.onFailure((Integer) response[0], (String) response[1], exception);
                } else {
                    AbLogUtil.e(mContext, "FAILURE_MESSAGE " + AbAppConfig.MISSING_PARAMETERS);
                }

                break;
            case AbHttpClient.START_MESSAGE:
                mResultResponseListener.initProgressDialog();
                mResultResponseListener.onStart();
                break;
            case AbHttpClient.FINISH_MESSAGE:
                mResultResponseListener.onFinish();
                mResultResponseListener.dismissProgressDialog();
                break;
            case AbHttpClient.PROGRESS_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length >= 2) {
                    mResultResponseListener.onProgress((Integer) response[0], (Integer) response[1]);
                } else {
                    AbLogUtil.e(mContext, "PROGRESS_MESSAGE " + AbAppConfig.MISSING_PARAMETERS);
                }
                break;
            case AbHttpClient.RETRY_MESSAGE:
                AbLogUtil.e("TAG", "RETRY_MESSAGE Message is receive");
                mResultResponseListener.onRetry();
                break;
            default:
        }
    }
}
