package rst.framework.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;

import rst.framework.BaseApplication;

/**
 * Created by iceman on 16/1/22.
 */
public class LogUtil {
    private static final String TAG = "APP_LOG";

    public static int LogLevel = Log.DEBUG;

    static {
        try {
            boolean isDebug = BaseApplication.getAppContext().isDebug();
            LogUtil.LogLevel = isDebug ? Log.DEBUG : Log.ERROR;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (Log.INFO >= LogLevel) {
            Log.i(tag, msg);
        }
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (Log.DEBUG >= LogLevel) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg) {
        d(TAG, msg);
    }
}
