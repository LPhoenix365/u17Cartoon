/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.framework.ab.http;

// TODO: Auto-generated Javadoc

import android.content.Context;

import com.paic.mhis.siapp.ab.util.AbLogUtil;
import com.paic.mhis.siapp.ab.network.HttpRequestTask;
import com.paic.mhis.siapp.main.base.SaveKeys;
import com.paic.mhis.siapp.view.dialog.ProgressDialogHandler;

/**
 * © 2012 amsoft.cn
 * 名称：AbStringHttpResponseListener.java
 * 描述：Http字符串响应监听器
 *
 * @author 还如一梦中
 * @version v1.0
 * @date：2013-11-13 上午9:00:52
 */
public abstract class AbStringHttpResponseListener extends AbHttpResponseListener {


    private ProgressDialogHandler mProgressDialogHandler;
    private boolean isShow;
    private int loadingType;

    /**
     * 构造.
     */
    public AbStringHttpResponseListener() {
        super();
    }

    public AbStringHttpResponseListener(final HttpRequestTask httpRequestTask) {
        this(httpRequestTask.getContext(), httpRequestTask.getLoadingType(), httpRequestTask.isCancelable());
    }

    public AbStringHttpResponseListener(Context context) {
        super();
        this.isShow = true;
        mProgressDialogHandler = new ProgressDialogHandler(context, true);
    }

    public AbStringHttpResponseListener(Context context, boolean isShow) {
        super();

        this.isShow = isShow;
        if (isShow) {
            mProgressDialogHandler = new ProgressDialogHandler(context, true);
        }
    }

    public AbStringHttpResponseListener(Context context, boolean isShow, boolean isCanCancel) {
        super();

        this.isShow = isShow;
        if (isShow) {
            mProgressDialogHandler = new ProgressDialogHandler(context, isCanCancel);
        }
    }

    public AbStringHttpResponseListener(Context context, int loadingType, boolean isCanCancel) {
        super();
        this.loadingType =loadingType;
        if (loadingType == SaveKeys.LoadingType.LOADING_TYPE_ZERO) { //不显示Loading
            return;
        }
        mProgressDialogHandler = new ProgressDialogHandler(context, isCanCancel);
        if (loadingType == SaveKeys.LoadingType.LOADING_TYPE_ONE) { //默认加载状态小人
            //initProgressDialog();
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        } else if (loadingType == SaveKeys.LoadingType.LOADING_TYPE_TWO) { //圈圈
            //initLoadingDialog();
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_LOADING_DIALOG).sendToTarget();
        }
    }


    @Override
    public void initProgressDialog() {
        if (mProgressDialogHandler != null && isShow) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }

    }

    @Override
    public void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
//			mProgressDialogHandler = null;
        }
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_LOADING_DIALOG).sendToTarget();
        }
    }

    @Override
    public void initLoadingDialog() {
        if (mProgressDialogHandler != null && isShow) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_LOADING_DIALOG).sendToTarget();
        }
    }


    /**
     * 描述：获取数据成功会调用这里.
     *
     * @param statusCode the status code
     * @param content    the content
     */
    public abstract void onSuccess(int statusCode, String content);


    /**
     * 成功消息.
     *
     * @param statusCode the status code
     * @param content    the content
     */
    public void sendSuccessMessage(int statusCode, String content) {
        sendMessage(obtainMessage(AbHttpClient.SUCCESS_MESSAGE, new Object[]{statusCode, content}));
    }

    @Override
    public void onStart() {
        AbLogUtil.d("onStart is running");
    }

    @Override
    public void onFinish() {
        AbLogUtil.d("onFinish is running");
    }

    public void onFailure(int statusCode, String content, Throwable error) {
        AbLogUtil.d("statusCode:" + statusCode + "content:" + content + " error:" + error.toString());
    }

}
