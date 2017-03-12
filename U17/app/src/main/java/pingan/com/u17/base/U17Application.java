package pingan.com.u17.base;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;


/**
 * Author：liupeng on 2017/2/6 23:45
 * Address：liupeng264@pingan.com.cn
 */
public class U17Application extends Application {

    private static U17Application INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        INSTANCE = this;
    }

    public static U17Application getInstance() {
      return INSTANCE;
    }






}
