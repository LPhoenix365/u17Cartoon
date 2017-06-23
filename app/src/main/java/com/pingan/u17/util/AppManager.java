package com.pingan.u17.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Iterator;
import java.util.Stack;

/**
 *Activity的堆栈工具类
 * Created by LIUQIANG928 on 2017-02-27.
 */
public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager      instance;

    private AppManager() {
    }

    public static AppManager getAppManager() {
        if(instance == null) {
            instance = new AppManager();
        }

        return instance;
    }

    public void addActivity(Activity activity) {
        if(activityStack == null) {
            activityStack = new Stack();
        }

        activityStack.add(activity);
    }

    public Activity currentActivity() {
        if(activityStack == null) {
            return null;
        } else {
            Activity activity = (Activity)activityStack.lastElement();
            return activity;
        }
    }

    public void finishActivity() {
        Activity activity = (Activity)activityStack.lastElement();
        this.finishActivity(activity);
    }

    public void finishActivity(Activity activity) {
        if(activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }

    }

    public void finishActivity(Class<?> cls) {
        Iterator var3 = activityStack.iterator();

        while(var3.hasNext()) {
            Activity activity = (Activity)var3.next();
            if(activity.getClass().equals(cls)) {
                this.finishActivity(activity);
            }
        }

    }

    public Activity findActivityByCalss(Class<?> cls) {
        Iterator var3 = activityStack.iterator();

        while(var3.hasNext()) {
            Activity activity = (Activity)var3.next();
            System.out.println(activity.getClass().getName());
            if(activity.getClass().equals(cls)) {
                return activity;
            }
        }

        return null;
    }

    public int getActivitySize() {
        int size = 0;
        if(activityStack != null) {
            size = activityStack.size();
        }

        return size;
    }

    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    public void finishAllActivity() {
        if(activityStack != null) {
            int i = 0;

            for(int size = activityStack.size(); i < size; ++i) {
                if(activityStack.get(i) != null) {
                    ((Activity)activityStack.get(i)).finish();
                }
            }

            activityStack.clear();
        }
    }

    public void AppExit(Context context) {
        try {
            this.finishAllActivity();
            ActivityManager activityMgr = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception var3) {

        }

    }
}
