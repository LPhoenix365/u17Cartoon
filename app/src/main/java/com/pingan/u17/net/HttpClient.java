package com.pingan.u17.net;

import com.example.framework.http.abutil.AbLogUtil;
import com.pingan.u17.base.U17Application;
import com.pingan.u17.util.AppEnvConstants;

import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/5/31
 */
public class HttpClient {
    private OkHttpClient okHttpClient;
    private Object       apiService;
    private HostnameVerifier hostnameVerifier;
    private SSLContext sslContext;


    private Cache initCache() {
        Cache cache = null;
        File cacheFile = new File(U17Application.getInstance().getCacheDir(), "/cacheFile");
        AbLogUtil.d("tag","path="+cacheFile.getAbsolutePath());
        ///data/data/com.pingan.u17Cartoon/cache/com.pingan.u17CartooncacheFile
        if (cache == null)
            cache = new Cache(cacheFile, 20 * 1024 * 1024);  //20M
        return cache;
    }


    public RestApi getApiService() {
        return (RestApi) apiService;
    }

    public <T> T getApi(Class<T> api) {
        return (T) apiService;
    }

    /**
     * 使用默认配置服务
     */
    public HttpClient() {
        Builder builder = new Builder();
        //这里设置的build 仅仅是层封装
        builder.setInterceptor(new RequestEncryptInterceptor())
                .setRestfulApi(RestApi.class)
                .setCache(initCache())
                .setHostIp(AppEnvConstants.BASE_URL);
        createHttpClinet(builder);
    }

    private void createHttpClinet(Builder builder) {
        okHttpClient = genericClient(builder);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(builder.getHostIp())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        apiService = retrofit.create(builder.getRestfulApi());
    }

    // 创建OkHttpClient实例
    private OkHttpClient genericClient(Builder builder) {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(builder.getCache())
                .addInterceptor(builder.getInterceptor());
        return httpBuilder.build();
    }


    public void getUnsafeHttpClient() {
        X509TrustManager[] trustAllCerts = new X509TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            //校验客户端证书
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            //校验服务端证书
                        if (chain == null) {
                            throw  new IllegalArgumentException("NUll Certs");
                        }
                        if (chain.length < 1) {
                            throw  new IllegalArgumentException("CHeck Certs is empty");
                        }
                        for (X509Certificate x509Certificate : chain) {
                            x509Certificate.checkValidity();
                        }
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };

        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, null);

            //未真正校验服务器证书域名
            hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    //未真正校验服务器证书域名
                    HostnameVerifier hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
                    hostnameVerifier.verify("域名规则",session);
                    return true;
                }
            };
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        OkHttpClient build = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .hostnameVerifier(hostnameVerifier)
                .sslSocketFactory(sslContext.getSocketFactory(), trustAllCerts[0])
                .build();

    }


}
