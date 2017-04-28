package rst.framework;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.pingan.sparta.ActFlighting;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import rst.framework.network.OkhttpUtil;
import rst.framework.utils.LogUtil;

/**
 * Created by iceman on 15/6/22.
 */
public abstract class BaseApplication extends Application {
    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        OkhttpUtil.init(buildSSLSocketFactory(this, R.raw.geotrust));
        ImagePipelineConfig imagePipelineConfig = null;
        try {
            imagePipelineConfig = OkHttpImagePipelineConfigFactory.newBuilder(this, OkhttpUtil.getUnsafeOkHttpClient())
                    .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                    .build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        Fresco.initialize(this, imagePipelineConfig);

        getSpartaParams();

//        if (getPackageName().equals(getCurProcessName(this))) {
//            HybridRouteManager.getInstance().init();
//        }

//        LocationManager.getLocationInstance().startLocation();
    }

    private void getSpartaParams(){
        ActFlighting af = new ActFlighting(this); // 参数为当前的context对象

        String spartaStr = "";
        try {
            spartaStr = af.actflighting();

            LogUtil.i("BaseApplication-123",spartaStr);
        } catch (Exception e) {

        }
    }

    public abstract boolean isDebug();

    private SSLSocketFactory buildSSLSocketFactory(Context context,
                                                   int certRawResId) {
        KeyStore keyStore = null;
        try {
            keyStore = buildKeyStore(context, certRawResId);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = null;
        try {
            tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sslContext.init(null, tmf.getTrustManagers(), null);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return sslContext.getSocketFactory();

    }

    private KeyStore buildKeyStore(Context context, int certRawResId)
            throws KeyStoreException, CertificateException,
            NoSuchAlgorithmException, IOException {
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        InputStream inputStream = context.getResources().openRawResource(
                certRawResId);
        keyStore.load(inputStream, "pw12306".toCharArray());
        return keyStore;
    }

    public static BaseApplication getAppContext() {
        return baseApplication;
    }
}
