package pingan.com.u17.util;

import android.content.Context;
import android.content.SharedPreferences;

import pingan.com.u17.base.U17Application;


/**
 * Author：liupeng on 2017/2/7 20:25
 * Address：liupeng264@pingan.com.cn
 */
public class SpUtils {

    private static final String SHARE_PREFS_NAME = "u17cartoon";
    private static SharedPreferences mSharedPreferences;
    private static Context mContext= U17Application.getInstance();

    public static void putInt(Context ctx, String key, int value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                    Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putInt(key, value).apply();

    }

    public static void putInt(String key, int value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = mContext.getSharedPreferences(SHARE_PREFS_NAME,
                    Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putInt(key, value).apply();

    }

    public static int getInt(Context ctx, String key,
                             int defaultValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                    Context.MODE_PRIVATE);
        }

        return mSharedPreferences.getInt(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = mContext.getSharedPreferences(SHARE_PREFS_NAME,
                    Context.MODE_PRIVATE);
        }

        return mSharedPreferences.getInt(key, defaultValue);
    }

    public static void putBoolean(Context ctx, String key, boolean value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                    Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static void putBoolean(String key, boolean value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = mContext.getSharedPreferences(SHARE_PREFS_NAME,
                    Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context ctx, String key,
                                     boolean defaultValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                    Context.MODE_PRIVATE);
        }

        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = mContext.getSharedPreferences(SHARE_PREFS_NAME,
                    Context.MODE_PRIVATE);
        }

        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public static void putString(String key, String value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = mContext.getSharedPreferences(SHARE_PREFS_NAME,
                    Context.MODE_PRIVATE);
        }

        mSharedPreferences.edit().putString(key, value).apply();
    }

    public static String getString(String key, String defaultValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = mContext.getSharedPreferences(SHARE_PREFS_NAME,
                    Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getString(key, defaultValue);
    }
}
