package rst.framework.network;

import android.content.Context;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * okhttp工具类,使用指定密钥库初始化okhttp
 */
public class OkhttpUtil {
    private static OkHttpClient mOkHttpClient;

    public static synchronized void init(SSLSocketFactory factory) {
        mOkHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(factory)//设置https证书
                .readTimeout(30, TimeUnit.SECONDS)//设置read超时时间
                .build();
    }


    public static OkHttpClient getmOkHttpClient() {
        return mOkHttpClient;
    }


    /**
     * 用于https图片加载,信任所有证书
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static OkHttpClient getUnsafeOkHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };
        final TrustManager[] trustAllCerts = new TrustManager[]{trustManager};
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts,
                new java.security.SecureRandom());
        final SSLSocketFactory sslSocketFactory = sslContext
                .getSocketFactory();


        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustManager)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
        return client;
    }

}
