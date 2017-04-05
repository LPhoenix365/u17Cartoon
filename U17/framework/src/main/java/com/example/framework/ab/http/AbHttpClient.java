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

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.paic.mhis.siapp.R;
import com.paic.mhis.siapp.ab.global.AbAppConfig;
import com.paic.mhis.siapp.ab.global.AbAppException;
import com.paic.mhis.siapp.ab.task.AbThreadFactory;
import com.paic.mhis.siapp.ab.util.AbAppUtil;
import com.paic.mhis.siapp.ab.util.AbFileUtil;
import com.paic.mhis.siapp.ab.util.AbLogUtil;
import com.paic.mhis.siapp.control.Session;
import com.paic.mhis.siapp.control.LogonInvaildHandle;
import com.paic.mhis.siapp.main.base.Contancts;
import com.paic.mhis.siapp.utils.CommonUtils;
import com.paic.mhis.siapp.view.ToastUtil;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Executor;

import javax.net.ssl.SSLHandshakeException;

// TODO: Auto-generated Javadoc

/**
 * © 2012 amsoft.cn 名称：AbHttpClient.java 描述：Http客户端
 *
 * @author 还如一梦中
 * @version v1.0 @date：2013-11-13 上午9:00:52
 */
public class AbHttpClient {

    /**
     * 上下文.
     */
    private static Context mContext;

    /**
     * 线程执行器.
     */
    public static Executor mExecutorService = null;

    /**
     * 编码.
     */
    private String encode = HTTP.UTF_8;

    /**
     * 用户代理.
     */
    private String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.43 BIDUBrowser/6.x Safari/537.31";

    private static final String HTTP_GET = "GET";
    private static final String HTTP_POST = "POST";
    private static final String USER_AGENT = "User-Agent";
    private static final String ACCEPT_ENCODING = "Accept-Encoding";
    /**
     * 最大连接数.
     */
    private static final int DEFAULT_MAX_CONNECTIONS = 10;

    /**
     * 超时时间.
     */
    public static final int DEFAULT_SOCKET_TIMEOUT = 30000;

    /**
     * 重试次数.
     */
    private static int DEFAULT_MAX_RETRIES = 3;

    /**
     * 缓冲大小.
     */
    private static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192 * 2;

    /**
     * 缓冲大小.
     */
    private static final int BUFFER_SIZE = 4096;

    /**
     * 成功.
     */
    protected static final int SUCCESS_MESSAGE = 0;

    /**
     * 失败.
     */
    protected static final int FAILURE_MESSAGE = 1;

    /**
     * 和网络相关的失败.
     */
    protected static final int FAILURE_MESSAGE_CONNECT = 2;

    /**
     * 和服务相关的失败.
     */
    protected static final int FAILURE_MESSAGE_SERVICE = 3;

    /**
     * 开始.
     */
    protected static final int START_MESSAGE = 4;

    /**
     * 完成.
     */
    protected static final int FINISH_MESSAGE = 5;

    /**
     * 进行中.
     */
    protected static final int PROGRESS_MESSAGE = 6;

    /**
     * 重试.
     */
    protected static final int RETRY_MESSAGE = 7;

    /**
     * 超时时间.
     */
    private int mTimeout = DEFAULT_SOCKET_TIMEOUT;

    /**
     * 通用证书. 如果要求HTTPS连接，则使用SSL打开连接
     */
    private boolean mIsOpenEasySSL = true;

    /**
     * HTTP Client
     */
    private DefaultHttpClient mHttpClient = null;

    /**
     * HTTP 上下文
     */
    private HttpContext mHttpContext = null;

    /**
     * 初始化.
     *
     * @param context the context
     */
    public AbHttpClient(Context context) {
        mContext = context;
        mExecutorService = AbThreadFactory.getExecutorService();
        mHttpContext = new BasicHttpContext();
    }

    /**
     * 描述：带参数的get请求.
     *
     * @param url              the url
     * @param params           the params
     * @param responseListener the response listener
     */
    public void get(final String url, final AbRequestParams params, final AbHttpResponseListener responseListener) {

        responseListener.setHandler(new ResponderHandler(responseListener));
        if (!CommonUtils.checkNet(mContext)) {
//			new CustomDialog(mContext).showDialog(R.string.net_erro);
            //ToastUtil.showToast(mContext, R.string.toast_http_fail_right);
            responseListener.dismissProgressDialog();//关闭Dialog
            responseListener.onFailure(AbHttpStatus.NO_NETWORK,"网络链接不可用，请稍候再试",new Throwable());
			return;
        }
        mExecutorService.execute(new Runnable() {
            public void run() {
                try {
                    doGet(url, params, responseListener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 描述：带参数的get请求.
     *
     * @param url              the url
     * @param params           the params
     * @param responseListener the response listener
     */
    public void getWebData(final String url, final AbRequestParams params, final AbHttpResponseListener responseListener) {

        responseListener.setHandler(new ResponderHandler(responseListener));
        if (!CommonUtils.checkNet(mContext)) {
//			new CustomDialog(mContext).showDialog(R.string.net_erro);
            ToastUtil.showToast(mContext, R.string.toast_http_fail_right);
//			return;
        }
        mExecutorService.execute(new Runnable() {
            public void run() {
                try {
                    doGet(url, params, responseListener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 描述：带参数的JSONPost请求.
     *
     * @param url              the url
     * @param params           the params
     * @param responseListener the response listener
     */
    public void postJSON(final String url, final JSONObject params, final AbHttpResponseListener responseListener) {

        responseListener.setHandler(new ResponderHandler(responseListener));
        if (!CommonUtils.checkNet(mContext)) {
            // new CustomDialog(mContext).showDialog(R.string.net_erro);
            ToastUtil.showToast(mContext, R.string.toast_http_fail_right);
            //return;
        }
        mExecutorService.execute(new Runnable() {
            public void run() {
                try {
                    doJSONPost(url, params, responseListener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 描述：带参数的post请求.
     *
     * @param url              the url
     * @param params           the params
     * @param responseListener the response listener
     */
    public void post(final String url, final AbRequestParams params, final AbHttpResponseListener responseListener) {
        responseListener.setHandler(new ResponderHandler(responseListener));
        if (!CommonUtils.checkNet(mContext)) {
//			new CustomDialog(mContext).showDialog(R.string.net_erro);
            ToastUtil.showToast(mContext, R.string.toast_http_fail_right);
//			return;
        }
        mExecutorService.execute(new Runnable() {
            public void run() {
                try {
                    doPost(url, params, responseListener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 描述 带参数的文件上传
     */
    public void dopost(final String url, final AbRequestParams params, final AbHttpResponseListener responseListener, final Map<String, String> header) {
        responseListener.setHandler(new ResponderHandler(responseListener));
        if (!CommonUtils.checkNet(mContext)) {
//			new CustomDialog(mContext).showDialog(R.string.net_erro);
            ToastUtil.showToast(mContext, R.string.toast_http_fail_right);
//			return;
        }
        mExecutorService.execute(new Runnable() {
            public void run() {
                try {
                    uploadFile(url, params, responseListener, header);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void delete(final String url, final JSONObject params, final AbHttpResponseListener responseListener) {
        responseListener.setHandler(new ResponderHandler(responseListener));
        if (!CommonUtils.checkNet(mContext)) {
//			new CustomDialog(mContext).showDialog(R.string.net_erro);
            ToastUtil.showToast(mContext, R.string.toast_http_fail_right);
//			return;
        }
        mExecutorService.execute(new Runnable() {
            public void run() {
                try {
                    doDelete(url, params, responseListener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 描述：执行get请求.
     *
     * @param url              the url
     * @param params           the params
     * @param responseListener the response listener
     */
    private void doGet(String url, AbRequestParams params, AbHttpResponseListener responseListener) {
        try {

            responseListener.sendStartMessage();


            if (!AbAppUtil.isNetworkAvailable(mContext)) {
                responseListener.sendFailureMessage(AbHttpStatus.CONNECT_FAILURE_CODE, AbAppConfig.CONNECT_EXCEPTION,
                        new AbAppException(AbAppConfig.CONNECT_EXCEPTION));
                return;
            }
            if (!url.endsWith("?") && !url.contains("?")) {
                url = url + "?";
            }
            if (params != null) {
                String paramsContent = params.getParamString();
                if (paramsContent != null) {
                    if (paramsContent.contains("xwalk")) {

                        url = url.substring(0, url.length() - 1);
                    } else {
                        url += paramsContent;
                    }

                }
            }
            // HttpGet连接对象

            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader(USER_AGENT, userAgent);
            httpGet.addHeader("token", getToken());
            // 修改 绍兴市民卡需动态改变ZoneCode by 2016-08-8
            if (CommonUtils.isEmpty(Contancts.CitizenZoneCode)) {
                httpGet.addHeader("zoneCode", getZoneCode());
            } else {
                httpGet.addHeader("zoneCode", Contancts.CitizenZoneCode);
            }
            httpGet.addHeader("areaCode",getAreaCode());
            // 压缩
            httpGet.addHeader(ACCEPT_ENCODING, "gzip");

            httpGet.addHeader("Content-Type", "text/html;charset=UTF-8");
            AbLogUtil.i("HttpHeader", "token:" + getToken() + "---zoneCode:" + getZoneCode());
            // 取得默认的HttpClient9
            HttpClient httpClient = getHttpClient();
            // 取得HttpResponse
            String response = httpClient.execute(httpGet, new RedirectionResponseHandler(url, responseListener),
                    mHttpContext);
            AbLogUtil.i(mContext, "[HTTP Request]:" + url + ",result：" + response);
//			AbLogUtil.i("test", url + ",result：" + response);
        } catch (Exception e) {
            e.printStackTrace();
            // 发送失败消息
            AbLogUtil.e("test", "Exception:" + e.getMessage());
            responseListener.sendFailureMessage(AbHttpStatus.UNTREATED_CODE, e.getMessage(), new AbAppException(e));
        } finally {
            responseListener.sendFinishMessage();
        }
    }

    /**
     * 描述：执行delete请求.
     *
     * @param url              the url
     * @param params           the params
     * @param responseListener the response listener
     */
    private void doDelete(String url, JSONObject params, AbHttpResponseListener responseListener) {
        try {

            responseListener.sendStartMessage();

            if (!AbAppUtil.isNetworkAvailable(mContext)) {
                responseListener.sendFailureMessage(AbHttpStatus.CONNECT_FAILURE_CODE, AbAppConfig.CONNECT_EXCEPTION,
                        new AbAppException(AbAppConfig.CONNECT_EXCEPTION));
                return;
            }

            if (!url.endsWith("?")) {
                url = url + "?";
            }


//			HttpDelete httpdelete = new HttpDelete(url);
            HttpDeleteWithBody httpdelete = new HttpDeleteWithBody(url);
            // json 处理

            httpdelete.setHeader("Content-Type", "application/json; charset=UTF-8");//or addHeader();

            httpdelete.setHeader("X-Requested-With", "XMLHttpRequest");

            //设置HttpDelete的请求参数
            httpdelete.setEntity(new StringEntity(params.toString()));
            httpdelete.addHeader("token", getToken());

            httpdelete.addHeader("zoneCode", getZoneCode());
            httpdelete.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);

            httpdelete.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
            HttpClient httpClient = getHttpClient();
            String response = httpClient.execute(httpdelete, new RedirectionResponseHandler(url, responseListener), mHttpContext);

            AbLogUtil.i(mContext, "[HTTP Request]:" + url + ",result：" + response);
//			AbLogUtil.i("test", url + ",result：" + response);
        } catch (Exception e) {
            e.printStackTrace();
            // 发送失败消息
            responseListener.sendFailureMessage(AbHttpStatus.UNTREATED_CODE, e.getMessage(), new AbAppException(e));
        } finally {
            responseListener.sendFinishMessage();
        }
    }

    /**
     * 描述：执行post请求.
     *
     * @param url              the url
     * @param params           the params
     * @param responseListener the response listener
     */
    private void doPost(String url, AbRequestParams params, AbHttpResponseListener responseListener) {
        try {
            responseListener.sendStartMessage();

            if (!AbAppUtil.isNetworkAvailable(mContext)) {
                responseListener.sendFailureMessage(AbHttpStatus.CONNECT_FAILURE_CODE, AbAppConfig.CONNECT_EXCEPTION,
                        new AbAppException(AbAppConfig.CONNECT_EXCEPTION));
                return;
            }

            // HttpPost连接对象
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(USER_AGENT, userAgent);

            httpPost.addHeader("token", getToken());

            httpPost.addHeader("zoneCode", getZoneCode());

            AbLogUtil.i("test", "token:" + getToken() + ",zoneCode:" + getZoneCode());

            httpPost.addHeader("Content-Type", "text/html;charset=UTF-8");
            // 压缩
            httpPost.addHeader(ACCEPT_ENCODING, "gzip");
            if (params != null) {
                // 使用NameValuePair来保存要传递的Post参数设置字符集
                HttpEntity httpentity = params.getEntity(responseListener);
                // 请求httpRequest
                httpPost.setEntity(httpentity);
            }

            // 取得默认的HttpClient
            HttpClient httpClient = getHttpClient();
            // 取得HttpResponse
            String response = httpClient.execute(httpPost, new RedirectionResponseHandler(url, responseListener),
                    mHttpContext);
            AbLogUtil.i(mContext, "request：" + url + ",result：" + response);
//			AbLogUtil.i("test", "request：" + url + ",result：" + response);

        } catch (Exception e) {
            e.printStackTrace();
            AbLogUtil.i(mContext, "request：" + url + ",error：" + e.getMessage());
            // 发送失败消息
            responseListener.sendFailureMessage(AbHttpStatus.UNTREATED_CODE, e.getMessage(), new AbAppException(e));
        } finally {
            responseListener.sendFinishMessage();
        }
    }

    /**
     * 文件上传
     */
    private void uploadFile(String url, AbRequestParams params, AbHttpResponseListener responseListener, Map<String, String> header) {
        try {
            responseListener.sendStartMessage();

            if (!AbAppUtil.isNetworkAvailable(mContext)) {
                responseListener.sendFailureMessage(AbHttpStatus.CONNECT_FAILURE_CODE, AbAppConfig.CONNECT_EXCEPTION,
                        new AbAppException(AbAppConfig.CONNECT_EXCEPTION));
                return;
            }
            String boundary = "-------------------------------7d273jdn23dd";
            // HttpPost连接对象
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(USER_AGENT, userAgent);
            if (header != null) {
                for (String key : header.keySet()) {
                    String value = header.get(key);
                    httpPost.addHeader(key, value);
                }

                if (!header.containsKey("token")) {
                    httpPost.addHeader("token", getToken());
                }
                if (!header.containsKey("zoneCode")) {
                    httpPost.addHeader("zoneCode", getZoneCode());
                }
            } else {
                httpPost.addHeader("token", getToken());
                httpPost.addHeader("zoneCode", getZoneCode());
            }

            httpPost.addHeader("Content-Type", "multipart/form-data;boundary=" + boundary);
            httpPost.addHeader("charset", "UTF-8");
            // 压缩
            httpPost.addHeader(ACCEPT_ENCODING, "gzip");

            AbLogUtil.i("HttpHeader", "token:" + getToken() + "---zoneCode:" + getZoneCode());

            if (params != null) {
                // 使用NameValuePair来保存要传递的Post参数设置字符集
                HttpEntity httpentity = params.getuploadFileEntity(responseListener);
                // 请求httpRequest
                httpPost.setEntity(httpentity);
            }

            // 取得默认的HttpClient
            HttpClient httpClient = getHttpClient(boundary);
//            httpClient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false);
            // 取得HttpResponse
            String response = httpClient.execute(httpPost, new RedirectionResponseHandler(url, responseListener),
                    mHttpContext);
            AbLogUtil.i(mContext, "request：" + url + ",result：" + response);
//			AbLogUtil.i("test", "request：" + url + ",result：" + response);

        } catch (
                Exception e
                )

        {
            e.printStackTrace();
            AbLogUtil.i(mContext, "request：" + url + ",error：" + e.getMessage());
            // 发送失败消息
            responseListener.sendFailureMessage(AbHttpStatus.UNTREATED_CODE, e.getMessage(), new AbAppException(e));
        } finally

        {
            responseListener.sendFinishMessage();
        }
    }

    /**
     * 描述：执行JSONPost请求.
     *
     * @param url              the url
     * @param params           the params
     * @param responseListener the response listener
     */
    private void doJSONPost(String url, JSONObject params, AbHttpResponseListener responseListener) {
        try {
            responseListener.sendStartMessage();

            if (!AbAppUtil.isNetworkAvailable(mContext)) {
                responseListener.sendFailureMessage(AbHttpStatus.CONNECT_FAILURE_CODE, AbAppConfig.CONNECT_EXCEPTION,
                        new AbAppException(AbAppConfig.CONNECT_EXCEPTION));
                return;
            }

            // HttpPost连接对象
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(USER_AGENT, userAgent);

            httpPost.addHeader("token", getToken());

            httpPost.addHeader("Authorization", "900000010311 8741198a2e5949169dff0a2cdacb3d24");

            httpPost.addHeader("zoneCode", getZoneCode());

            httpPost.addHeader("Content-Type", "application/json");

            AbLogUtil.i(mContext, "token：" + getToken() + ",zoneCode：" + getZoneCode());
            // 压缩
            httpPost.addHeader(ACCEPT_ENCODING, "gzip");
            if (params != null) {
                // 使用NameValuePair来保存要传递的Post参数设置字符集
                StringEntity httpentity = new StringEntity(params.toString(), "UTF-8");
                // 请求httpRequest
                httpPost.setEntity(httpentity);

                AbLogUtil.i(mContext, "request：" + url + ",params：" + params.toString());
            }


            // 取得默认的HttpClient
            HttpClient httpClient = getHttpClient();
            // 取得HttpResponse
            String response = httpClient.execute(httpPost, new RedirectionResponseHandler(url, responseListener),
                    mHttpContext);
            AbLogUtil.i(mContext, "request：" + url + ",result：" + response);
//			AbLogUtil.i("test", "request：" + url + ",result：" + response);

        } catch (Exception e) {
            e.printStackTrace();
            AbLogUtil.i(mContext, "request：" + url + ",error：" + e.getMessage());
//			AbLogUtil.i("test", "request：" + url + ",error：" + e.getMessage());
            // 发送失败消息
            responseListener.sendFailureMessage(AbHttpStatus.UNTREATED_CODE, e.getMessage(), new AbAppException(e));
        } finally {
            responseListener.sendFinishMessage();
        }
    }

    /**
     * 描述：写入文件并回调进度.
     *
     * @param context          the context
     * @param entity           the entity
     * @param name             the name
     * @param responseListener the response listener
     */
    public void writeResponseData(Context context, HttpEntity entity, String name,
                                  AbFileHttpResponseListener responseListener) {

        if (entity == null) {
            return;
        }

        if (responseListener.getFile() == null) {
            // 创建缓存文件
            responseListener.setFile(context, name);
        }

        InputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = entity.getContent();
            long contentLength = entity.getContentLength();
            outStream = new FileOutputStream(responseListener.getFile());
            if (inStream != null) {

                byte[] tmp = new byte[BUFFER_SIZE];
                int l, count = 0;
                while ((l = inStream.read(tmp)) != -1 && !Thread.currentThread().isInterrupted()) {
                    count += l;
                    outStream.write(tmp, 0, l);
                    responseListener.sendProgressMessage(count, (int) contentLength);
                }
            }
            responseListener.sendSuccessMessage(200);
        } catch (Exception e) {
            e.printStackTrace();
            // 发送失败消息
            responseListener.sendFailureMessage(AbHttpStatus.RESPONSE_TIMEOUT_CODE,
                    AbAppConfig.SOCKET_TIMEOUT_EXCEPTION, e);
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
                if (outStream != null) {
                    outStream.flush();
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 描述：转换为二进制并回调进度.
     *
     * @param entity           the entity
     * @param responseListener the response listener
     */
    public void readResponseData(HttpEntity entity, AbBinaryHttpResponseListener responseListener) {

        if (entity == null) {
            return;
        }

        InputStream inStream = null;
        ByteArrayOutputStream outSteam = null;

        try {
            inStream = entity.getContent();
            outSteam = new ByteArrayOutputStream();
            long contentLength = entity.getContentLength();
            if (inStream != null) {
                int l, count = 0;
                byte[] tmp = new byte[BUFFER_SIZE];
                while ((l = inStream.read(tmp)) != -1) {
                    count += l;
                    outSteam.write(tmp, 0, l);
                    responseListener.sendProgressMessage(count, (int) contentLength);

                }
            }
            responseListener.sendSuccessMessage(HttpStatus.SC_OK, outSteam.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            // 发送失败消息
            responseListener.sendFailureMessage(AbHttpStatus.RESPONSE_TIMEOUT_CODE,
                    AbAppConfig.SOCKET_TIMEOUT_EXCEPTION, e);
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
                if (outSteam != null) {
                    outSteam.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 描述：设置连接超时时间.
     *
     * @param timeout 毫秒
     */
    public void setTimeout(int timeout) {
        this.mTimeout = timeout;
    }

    /**
     * © 2012 amsoft.cn 名称：ResponderHandler.java 描述：请求返回
     *
     * @author 还如一梦中
     * @version v1.0 @date：2013-11-13 下午3:22:30
     */
    private static class ResponderHandler extends Handler {

        /**
         * 响应数据.
         */
        private Object[] response;

        /**
         * 响应消息监听.
         */
        private AbHttpResponseListener responseListener;

        /**
         * 响应消息处理.
         *
         * @param responseListener the response listener
         */
        public ResponderHandler(AbHttpResponseListener responseListener) {
            this.responseListener = responseListener;
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.Handler#handleMessage(android.os.Message)
         */
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case SUCCESS_MESSAGE:
                    response = (Object[]) msg.obj;

                    if (response != null) {
                        if (responseListener instanceof AbStringHttpResponseListener) {
                            if (response.length >= 2) {
                                ((AbStringHttpResponseListener) responseListener).onSuccess((Integer) response[0],
                                        (String) response[1]);
                            } else {
                                AbLogUtil.e(mContext, "SUCCESS_MESSAGE " + AbAppConfig.MISSING_PARAMETERS);
                            }

                        } else if (responseListener instanceof AbBinaryHttpResponseListener) {
                            if (response.length >= 2) {
                                ((AbBinaryHttpResponseListener) responseListener).onSuccess((Integer) response[0],
                                        (byte[]) response[1]);
                            } else {
                                AbLogUtil.e(mContext, "SUCCESS_MESSAGE " + AbAppConfig.MISSING_PARAMETERS);
                            }
                        } else if (responseListener instanceof AbFileHttpResponseListener) {

                            if (response.length >= 1) {
                                AbFileHttpResponseListener mAbFileHttpResponseListener = ((AbFileHttpResponseListener) responseListener);
                                mAbFileHttpResponseListener.onSuccess((Integer) response[0],
                                        mAbFileHttpResponseListener.getFile());
                            } else {
                                AbLogUtil.e(mContext, "SUCCESS_MESSAGE " + AbAppConfig.MISSING_PARAMETERS);
                            }

                        }
                    }
                    break;
                case FAILURE_MESSAGE:
                    response = (Object[]) msg.obj;
                    AbLogUtil.e("TAG", "response:" + response.toString());
                    if (response != null && response.length >= 3) {
                        // 异常转换为可提示的
                        int stateCode = (Integer) response[0];
                        AbLogUtil.e("stateCode", "stateCode:" + stateCode);
                        if (stateCode == HttpStatus.SC_UNAUTHORIZED) {  //401:手机号被挤掉
                            LogonInvaildHandle.onSessionInvaild(mContext);
                            return;
                        } else if (stateCode == 430) {//社保卡被别的手机绑定
                            LogonInvaildHandle.onCardInValid(mContext);
                            return;
                        }
                        AbAppException exception = new AbAppException((Exception) response[2]);
                        responseListener.onFailure((Integer) response[0], (String) response[1], exception);
                    } else {
                        AbLogUtil.e(mContext, "FAILURE_MESSAGE " + AbAppConfig.MISSING_PARAMETERS);
                    }
                    break;
                case START_MESSAGE:
                    responseListener.initProgressDialog();
                    responseListener.onStart();
                    break;
                case FINISH_MESSAGE:
                    responseListener.onFinish();
                    responseListener.dismissProgressDialog();
                    break;
                case PROGRESS_MESSAGE:
                    response = (Object[]) msg.obj;
                    if (response != null && response.length >= 2) {
                        responseListener.onProgress((Integer) response[0], (Integer) response[1]);
                    } else {
                        AbLogUtil.e(mContext, "PROGRESS_MESSAGE " + AbAppConfig.MISSING_PARAMETERS);
                    }
                    break;
                case RETRY_MESSAGE:
                    AbLogUtil.e("TAG", "RETRY_MESSAGE Message is receive");
                    responseListener.onRetry();
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * HTTP参数配置
     *
     * @return
     */
    public BasicHttpParams getHttpParams() {

        BasicHttpParams httpParams = new BasicHttpParams();

        // 设置每个路由最大连接数
        ConnPerRouteBean connPerRoute = new ConnPerRouteBean(30);
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams, connPerRoute);
        HttpConnectionParams.setStaleCheckingEnabled(httpParams, false);
        // 从连接池中取连接的超时时间，设置为1秒
        ConnManagerParams.setTimeout(httpParams, mTimeout);
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams, new ConnPerRouteBean(DEFAULT_MAX_CONNECTIONS));
        ConnManagerParams.setMaxTotalConnections(httpParams, DEFAULT_MAX_CONNECTIONS);
        // 读响应数据的超时时间
        HttpConnectionParams.setSoTimeout(httpParams, mTimeout);
        HttpConnectionParams.setConnectionTimeout(httpParams, mTimeout);
        HttpConnectionParams.setTcpNoDelay(httpParams, true);
        HttpConnectionParams.setSocketBufferSize(httpParams, DEFAULT_SOCKET_BUFFER_SIZE);

        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(httpParams, userAgent);
        // 默认参数
        HttpClientParams.setRedirecting(httpParams, false);
        HttpClientParams.setCookiePolicy(httpParams, CookiePolicy.BROWSER_COMPATIBILITY);
        httpParams.setParameter(ConnRoutePNames.DEFAULT_PROXY, null);
        return httpParams;

    }

    /**
     * 获取HttpClient，自签名的证书，如果想做签名参考AuthSSLProtocolSocketFactory类
     *
     * @return
     */
    public HttpClient getHttpClient() {
        if (mHttpClient != null) {
            return mHttpClient;
        } else {
            return createHttpClient();
        }
    }

    /**
     * 得到默认的httpClient
     */
    public HttpClient getHttpClient(String parmas) {

        if (mHttpClient != null) {
            return new DefaultHttpClient();
        } else {
            return new DefaultHttpClient();
        }
    }

    /**
     * 获取HttpClient，自签名的证书，如果想做签名参考AuthSSLProtocolSocketFactory类
     *
     * @return
     */
    public HttpClient createHttpClient() {
        BasicHttpParams httpParams = getHttpParams();

        if (mIsOpenEasySSL) {
            // 支持https的 SSL自签名的实现类
            AuthSSLProtocolSocketFactory authSSLProtocolSocketFactory = new AuthSSLProtocolSocketFactory(-1, null, R.raw.geotrust, "pw12306", mContext);
            SchemeRegistry supportedSchemes = new SchemeRegistry();
            SocketFactory socketFactory = PlainSocketFactory.getSocketFactory();
            supportedSchemes.register(new Scheme("http", socketFactory, 80));
            supportedSchemes.register(new Scheme("https", authSSLProtocolSocketFactory, 443));
            // 安全的ThreadSafeClientConnManager，否则不能用单例的HttpClient
            ClientConnectionManager connectionManager = new ThreadSafeClientConnManager(httpParams, supportedSchemes);
            // 取得HttpClient ThreadSafeClientConnManager
            mHttpClient = new DefaultHttpClient(connectionManager, httpParams);

        } else {
            // 线程安全的HttpClient
            mHttpClient = new DefaultHttpClient(httpParams);

        }
        mHttpClient.setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                // 兑现'keep-alive'头部信息
                HeaderElementIterator it = new BasicHeaderElementIterator(
                        response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch (NumberFormatException ignore) {
                        }
                    }
                }
                HttpHost target = (HttpHost) context.getAttribute(
                        ExecutionContext.HTTP_TARGET_HOST);
                if ("www.naughty-server.com".equalsIgnoreCase(target.getHostName())) {
                    // 只保持活动5秒
                    return 5 * 1000;
                } else {
                    // 否则保持活动30秒
                    return 30 * 1000;
                }
            }
        });

        mHttpClient.setReuseStrategy(new DefaultConnectionReuseStrategy());

        // 自动重试
        mHttpClient.setHttpRequestRetryHandler(mRequestRetryHandler);

        return mHttpClient;
    }

    /**
     * 是否打开ssl 自签名
     */
    public boolean isOpenEasySSL() {
        return mIsOpenEasySSL;
    }

    /**
     * 打开ssl 自签名
     *
     * @param isOpenEasySSL
     */
    public void setOpenEasySSL(boolean isOpenEasySSL) {
        this.mIsOpenEasySSL = isOpenEasySSL;
    }

    /**
     * 使用ResponseHandler接口处理响应,支持重定向
     */
    private class RedirectionResponseHandler implements ResponseHandler<String> {

        private AbHttpResponseListener mResponseListener = null;
        private String mUrl = null;

        public RedirectionResponseHandler(String url, AbHttpResponseListener responseListener) {
            super();
            this.mUrl = url;
            this.mResponseListener = responseListener;
        }

        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            HttpUriRequest request = (HttpUriRequest) mHttpContext.getAttribute(ExecutionContext.HTTP_REQUEST);
            // 请求成功
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            String responseBody = null;
            // 200直接返回结果
            if (statusCode == HttpStatus.SC_OK) {

                // 不打算读取response body
                // 调用request的abort方法
                // request.abort();

                if (entity != null) {
                    if (mResponseListener instanceof AbStringHttpResponseListener) {
                        // entity中的内容只能读取一次,否则Content has been consumed
                        // 如果压缩要解压
                        Header header = entity.getContentEncoding();
                        if (header != null) {
                            String contentEncoding = header.getValue();
                            if (contentEncoding != null) {
                                if (contentEncoding.contains("gzip")) {
                                    entity = new AbGzipDecompressingEntity(entity);
                                }
                            }
                        }
                        String charset = EntityUtils.getContentCharSet(entity) == null ? encode
                                : EntityUtils.getContentCharSet(entity);
                        responseBody = new String(EntityUtils.toByteArray(entity), charset);

                        ((AbStringHttpResponseListener) mResponseListener).sendSuccessMessage(statusCode, responseBody);
                    } else if (mResponseListener instanceof AbBinaryHttpResponseListener) {
                        responseBody = "Binary";
                        readResponseData(entity, ((AbBinaryHttpResponseListener) mResponseListener));
                    } else if (mResponseListener instanceof AbFileHttpResponseListener) {
                        // 获取文件名
                        String fileName = AbFileUtil.getCacheFileNameFromUrl(mUrl, response);
                        writeResponseData(mContext, entity, fileName, ((AbFileHttpResponseListener) mResponseListener));
                    }
                    // 资源释放!!!
                    try {
                        entity.consumeContent();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return responseBody;
                }

            }
            // 301 302进行重定向请求
            else if (statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_MOVED_PERMANENTLY) {
                // 从头中取出转向的地址
                Header locationHeader = response.getLastHeader("location");
                String location = locationHeader.getValue();
                if (request.getMethod().equalsIgnoreCase(HTTP_POST)) {
                    doPost(location, null, mResponseListener);
                } else if (request.getMethod().equalsIgnoreCase(HTTP_GET)) {
                    doGet(location, null, mResponseListener);
                }
            } else if (statusCode == HttpStatus.SC_NOT_FOUND) {
                // 404
                mResponseListener.sendFailureMessage(statusCode, AbAppConfig.NOT_FOUND_EXCEPTION,
                        new AbAppException(AbAppConfig.NOT_FOUND_EXCEPTION));
            } else {
                mResponseListener.sendFailureMessage(statusCode, AbAppConfig.REMOTE_SERVICE_EXCEPTION,
                        new AbAppException(AbAppConfig.REMOTE_SERVICE_EXCEPTION));
            }
            return null;
        }

    }

    /**
     * 自动重试处理
     */
    private HttpRequestRetryHandler mRequestRetryHandler = new DefaultHttpRequestRetryHandler() {

        // 自定义的恢复策略
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            // 设置恢复策略，在发生异常时候将自动重试DEFAULT_MAX_RETRIES次
            if (executionCount >= DEFAULT_MAX_RETRIES) {
                // 如果超过最大重试次数，那么就不要继续了
                AbLogUtil.e("TAG", "超过最大重试次数，不重试");
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                // 如果服务器丢掉了连接，那么就重试
                AbLogUtil.e("TAG", "服务器丢掉了连接，重试");
                return true;
            }
            if (exception instanceof SSLHandshakeException) {
                // SSL握手异常，不重试
                AbLogUtil.e("TAG", "ssl 异常 不重试");
                return false;
            }
            HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
            boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
            if (!idempotent) {
                // 如果请求被认为是幂等的，那么就重试
                AbLogUtil.e("TAG", "请求被认为是幂等的，重试");
                return true;
            }
            if (exception != null) {
                AbLogUtil.e("TAG", "exception != null，重试");
                return true;
            }
            return false;
        }
    };

    /**
     * 获取用户代理
     *
     * @return
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * 设置用户代理
     *
     * @param userAgent
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * 获取编码
     *
     * @return
     */
    public String getEncode() {
        return encode;
    }

    /**
     * 设置编码
     *
     * @param encode
     */
    public void setEncode(String encode) {
        this.encode = encode;
    }

    /**
     * 关闭HttpClient
     */
    public void shutdown() {
        if (mHttpClient != null && mHttpClient.getConnectionManager() != null) {
            mHttpClient.getConnectionManager().shutdown();
        }
    }

    public String getToken() {
        AbLogUtil.d("getToken:"+Session.getInstance().getToken());
        return Session.getInstance().getToken();
    }

    public String getZoneCode() {
        AbLogUtil.d("getZoneCode:"+Session.getInstance().getZoneCode(mContext));
        return Session.getInstance().getZoneCode(mContext);
//		return "0086";
    }
    public String getAreaCode() {
        if (Session.getInstance().getZoneCode()!=null&&"0575".equals(Session.getInstance().getZoneCode())) {
            return Session.getInstance().getAreaCode();
        }
        return "";
    }

    /**
     * 描述：带参数的get请求.
     *
     * @param url              the url
     * @param params           the params
     * @param responseListener the response listener
     */
    public void get(final String url, final AbRequestParams params, final AbHttpResponseListener responseListener, final Map<String, String> header) {

        responseListener.setHandler(new ResponderHandler(responseListener));
        if (!CommonUtils.checkNet(mContext)) {
//			new CustomDialog(mContext).showDialog(R.string.net_erro);
            responseListener.dismissProgressDialog();//关闭Dialog
            responseListener.onFailure(AbHttpStatus.NO_NETWORK,"网络链接不可用，请稍候再试",new Throwable());
//            ToastUtil.showToast(mContext, R.string.toast_http_fail_right);
			return;
        }
        mExecutorService.execute(new Runnable() {
            public void run() {
                try {
                    doGet(url, params, responseListener, header);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 描述：执行get请求.
     *
     * @param url              the url
     * @param params           the params
     * @param responseListener the response listener
     */
    private void doGet(String url, AbRequestParams params, AbHttpResponseListener responseListener, Map<String, String> header) {
        try {
            responseListener.sendStartMessage();

            if (!AbAppUtil.isNetworkAvailable(mContext)) {
                responseListener.sendFailureMessage(AbHttpStatus.CONNECT_FAILURE_CODE, AbAppConfig.CONNECT_EXCEPTION,
                        new AbAppException(AbAppConfig.CONNECT_EXCEPTION));
                return;
            }

            if (!url.endsWith("?") && !url.contains("?")) {
                url = url + "?";
            }

            // HttpGet连接对象
            if (params != null) {
                url += params.getParamString();
            }
            HttpGet httpGet = new HttpGet(url);
            for (String key : header.keySet()) {
                String value = header.get(key);
                httpGet.addHeader(key, value);
            }
            httpGet.addHeader(USER_AGENT, userAgent);

            if (!header.containsKey("token") && !CommonUtils.isEmpty(getToken())) {
                httpGet.addHeader("token", getToken());
            }
            if (CommonUtils.isEmpty(Contancts.CitizenZoneCode)) {
                if (!header.containsKey("zoneCode")) {
                    httpGet.addHeader("zoneCode", getZoneCode());
                }
            } else {
                httpGet.addHeader("zoneCode", Contancts.CitizenZoneCode);
            }

            httpGet.addHeader("areaCode",getAreaCode());

            // 压缩
            httpGet.addHeader(ACCEPT_ENCODING, "gzip");

            httpGet.addHeader("Content-Type", "text/html;charset=UTF-8");

            Header header1[] = httpGet.getAllHeaders();

            String headerStr = "";
            for (int i=0,j=header1.length;i<j;i++){
                headerStr += header1[i].toString()+"----";
            }

            AbLogUtil.i("HeaderInfo",headerStr);

            AbLogUtil.i(mContext, "[HTTP Request]:" + url);

            // 取得默认的HttpClient
            HttpClient httpClient = getHttpClient();
            // 取得HttpResponse
            String response = httpClient.execute(httpGet, new RedirectionResponseHandler(url, responseListener),
                    mHttpContext);
            AbLogUtil.i(mContext, "[HTTP Request_Result]:" + url + ",result：" + response);
        } catch (Exception e) {
            e.printStackTrace();
            // 发送失败消息
            AbLogUtil.e("test", "Exception:" + e.getMessage());
            responseListener.sendFailureMessage(AbHttpStatus.UNTREATED_CODE, e.getMessage(), new AbAppException(e));
        } finally {
            if (responseListener != null) {
                responseListener.sendFinishMessage();
            }
        }
    }

    public static int getDefaultMaxRetries() {
        return DEFAULT_MAX_RETRIES;
    }

    public static void setDefaultMaxRetries(int defaultMaxRetries) {
        DEFAULT_MAX_RETRIES = defaultMaxRetries;
    }
}
