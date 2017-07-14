package com.example.framework.http.request;

import android.content.Context;

import com.example.framework.http.AbHttpStatus;
import com.example.framework.http.AbThreadFactory;
import com.example.framework.http.ResponderHandler;
import com.example.framework.http.abutil.AbAppUtil;
import com.example.framework.http.abutil.AbFileUtil;
import com.example.framework.http.abutil.AbLogUtil;
import com.example.framework.http.global.AbAppConfig;
import com.example.framework.http.global.AbAppException;
import com.example.framework.http.response.AbBinaryHttpResponseListener;
import com.example.framework.http.response.AbFileHttpResponseListener;
import com.example.framework.http.response.AbGzipDecompressingEntity;
import com.example.framework.http.response.AbHttpResponseListener;
import com.example.framework.http.response.AbStringHttpResponseListener;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
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
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/1
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

    private static final String HTTP_GET                = "GET";
    private static final String HTTP_POST               = "POST";
    private static final String USER_AGENT              = "User-Agent";
    private static final String ACCEPT_ENCODING         = "Accept-Encoding";
    /**
     * 最大连接数.
     */
    private static final int    DEFAULT_MAX_CONNECTIONS = 10;

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
    public static final int SUCCESS_MESSAGE = 0;

    /**
     * 失败.
     */
    public static final int FAILURE_MESSAGE = 1;

    /**
     * 和网络相关的失败.
     */
    public static final int FAILURE_MESSAGE_CONNECT = 2;

    /**
     * 和服务相关的失败.
     */
    public static final int FAILURE_MESSAGE_SERVICE = 3;

    /**
     * 开始.
     */
    public static final int START_MESSAGE = 4;

    /**
     * 完成.
     */
    public static final int FINISH_MESSAGE = 5;

    /**
     * 进行中.
     */
    public static final int PROGRESS_MESSAGE = 6;

    /**
     * 重试.
     */
    public static final int RETRY_MESSAGE = 7;

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


    public void get(final String url, final AbRequestParams requestParams, final AbHttpResponseListener resultResponseListener) {
        resultResponseListener.setHandler(new ResponderHandler(mContext, resultResponseListener));
        if (!AbAppUtil.isNetworkAvailable(mContext)) {
            resultResponseListener.dismissProgressDialog();//关闭Dialog
            resultResponseListener.onFailure(AbHttpStatus.NO_NETWORK, "网络链接不可用，请稍候再试", new Throwable());
            return;
        }
        mExecutorService.execute(new Runnable() {
            public void run() {
                try {
                    doGet(url, requestParams, resultResponseListener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void doGet(String url, AbRequestParams params, AbHttpResponseListener responseListener) {
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
                /*if (paramsContent.contains("xwalk")) {
                    url = url.substring(0, url.length() - 1);
                } else {
                    url += paramsContent;
                }*/
                url+=paramsContent;
            }
        }

        HttpGet httpGet = new HttpGet(url);
        AbLogUtil.d("http","request:"+url);
       //httpGet.addHeader(USER_AGENT, userAgent);
        //httpGet.addHeader("Content-Type", "text/html;charset=UTF-8");
        // 取得默认的HttpClient9
        HttpClient httpClient = getHttpClient();
        // 取得HttpResponse
        String response = null;
        try {
            response = httpClient.execute(httpGet, new RedirectionResponseHandler(url, responseListener), mHttpContext);
            AbLogUtil.d("http","response:"+response);
        } catch (IOException e) {
            e.printStackTrace();
            AbLogUtil.e("AbHttpClient", "e" + e);

        }finally {
           responseListener.sendFinishMessage();

        }
        AbLogUtil.i(mContext, "[HTTP Request]:" + url + ",result：" + response);

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
     * 使用ResponseHandler接口处理响应,支持重定向
     */
    private class RedirectionResponseHandler implements ResponseHandler<String> {

        private AbHttpResponseListener mResponseListener = null;
        private String                 mUrl              = null;

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
                    //  doPost(location, null, mResponseListener);
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
            responseListener.setFile(new File(name));
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
     * 获取HttpClient，自签名的证书，如果想做签名参考AuthSSLProtocolSocketFactory类
     *
     * @return
     */
    public HttpClient createHttpClient() {
        BasicHttpParams httpParams = getHttpParams();

        if (mIsOpenEasySSL) {
            // 支持https的 SSL自签名的实现类
           // AuthSSLProtocolSocketFactory authSSLProtocolSocketFactory = new AuthSSLProtocolSocketFactory(-1, null, R.raw.geotrust, "pw12306", mContext);
            SchemeRegistry supportedSchemes = new SchemeRegistry();
            SocketFactory socketFactory = PlainSocketFactory.getSocketFactory();
            supportedSchemes.register(new Scheme("http", socketFactory, 80));
           // supportedSchemes.register(new Scheme("https", authSSLProtocolSocketFactory, 443));
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
       // mHttpClient.setHttpRequestRetryHandler(mRequestRetryHandler);

        return mHttpClient;
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

}
