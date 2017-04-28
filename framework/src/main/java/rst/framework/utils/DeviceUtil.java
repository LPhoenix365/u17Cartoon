package rst.framework.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import SevenZip.Compression.LZMA.Base;
import rst.framework.BaseApplication;

/**
 * 设备信息工具类,用以获取屏幕分辨率,设备识别号等硬件信息
 */
public class DeviceUtil {
    private static int screenWidth = 0;
    private static int screenHeight = 0;
    private static int statusBarHeight = 0;

    public static boolean isTablet = false;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = BaseApplication.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    // 将px值转换为sp值，保证文字大小不变
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 获得状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            Class<?> c;
            Object obj;
            Field field;
            int x;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = BaseApplication.getAppContext().getResources().getDimensionPixelSize(x);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
//
//            View view = activity.getWindow().getDecorView();
//            Rect rect = new Rect();
//            view.getWindowVisibleDisplayFrame(rect);
//            statusBarHeight = rect.top;
        }
        return statusBarHeight;
    }

    public static int getScreenWidth() {
        if (screenWidth == 0) {
            screenWidth = BaseApplication.getAppContext().getResources().getDisplayMetrics().widthPixels;
        }
        return screenWidth;
    }

    public static DisplayMetrics getDisplayMetrics() {
        return BaseApplication.getAppContext().getResources().getDisplayMetrics();
    }

    public static int getScreenHeight() {
        if (screenHeight == 0) {
            screenHeight = BaseApplication.getAppContext().getResources().getDisplayMetrics().heightPixels;
        }
        return screenHeight;
    }

    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }


    /**
     * 判断是否平板
     *
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        double diagonalPixels = Math.sqrt(Math.pow(dm.widthPixels, 2) + Math.pow(dm.heightPixels, 2));
        double screenSize = diagonalPixels / (160 * dm.density);
        if (screenSize >= 6) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断sdk版本是否高于11.目前关系到属性动画的使用
     *
     * @return
     */
    public static boolean isHigherThanSDK11() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return true;
        }
        return false;
    }

    /**
     * 获取手机厂商
     *
     * @return
     */
    public static String getDeviceManufacturers() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取屏幕宽度和高度，单位为px
     * @param context
     * @return
     */
    public static Point getScreenMetrics(Context context){
        DisplayMetrics dm =context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        LogUtil.i( "Screen---Width = " + w_screen + " Height = " + h_screen + " densityDpi = " + dm.densityDpi);
        return new Point(w_screen, h_screen);

    }
    /**
     * 获取屏幕长宽比
     * @param context
     * @return
     */
    public static float getScreenRate(Context context){
        Point P = getScreenMetrics(context);
        float H = P.y;
        float W = P.x;
        return (H/W);
    }


    /**
     * 获取设备的IMEI
     */
    public static String getIMEI() {
        Context context = BaseApplication.getAppContext();
        if (null == context) {
            return "";
        }
        String imei = "";
        try {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            imei = tm.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imei;
    }

    /**
     * 获取当前进程名
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /*
     * 判断当前网络是否可用
     */

    public static boolean isHasNet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 用来判断服务是否运行.
     *
     * @param context the context
     * @param className 判断的服务名字 "com.xxx.xx..XXXService"
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> servicesList = activityManager.getRunningServices(Integer.MAX_VALUE);
        Iterator<ActivityManager.RunningServiceInfo> l = servicesList.iterator();
        while (l.hasNext()) {
            ActivityManager.RunningServiceInfo si = (ActivityManager.RunningServiceInfo) l.next();
            if (className.equals(si.service.getClassName())) {
                isRunning = true;
            }
        }
        return isRunning;
    }


}
