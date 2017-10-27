package com.pingan.u17.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.pingan.u17.base.U17Application;


public class ActivityIntentTools {
	
    public static void gotoActivity(Context context, Class<?> targetClass) {
        Intent intent = new Intent(context, targetClass);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public static void gotoActivityNotFinish(Context context, Class<?> targetClass) {
        Intent intent = new Intent(context, targetClass);
        context.startActivity(intent);
    }

    public static void gotoActivityWithBundle(Context context, Class<?> targetClass, Bundle bundle) {
        Intent intent = new Intent(context, targetClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public static void gotoActivityNotFinishWithBundle(Context context, Class<?> actClass, Bundle bundle) {
        Intent intent = new Intent(context, actClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void gotoActivityForResult(Activity activity, Class<?> actClass, int requestCode) {
        Intent intent = new Intent(activity, actClass);
        activity.startActivityForResult(intent, requestCode);
    }
    
    public static void gotoActivityForResult(Fragment fragment, Class<?> actClass, int requestCode) {
        Intent intent = new Intent(fragment.getActivity(), actClass);
        fragment.startActivityForResult(intent, requestCode);
    }
    
    public static void gotoActivityForResultWithBundle(Activity activity, Class<?> actClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(activity, actClass);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void gotoActivityForResultWithBundle(Fragment fragment, Class<?> actClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(fragment.getActivity(), actClass);
        intent.putExtras(bundle);
        fragment.startActivityForResult(intent, requestCode);
    }

    /**
     * 使用手机浏览器打开网页
     *
     * @param context
     * @param url
     */
    public static void startViewUrl(Context context, String url) {
        try {
            Uri u = Uri.parse(url);
            Intent it = new Intent(Intent.ACTION_VIEW, u);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(it);
        } catch (Exception e) {
            Toast.makeText(U17Application.getInstance(), "当前无可用浏览器", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 启动设置里的应用详情页（业务：设置权限等等）
     * @param context
     */
    public static void startAppSettings(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_SETTINGS);
//        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(intent);
    }

}
