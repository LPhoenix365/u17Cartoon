package com.pingan.u17.manager;

import com.pingan.u17.bean.ActivityBean;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/10/27
 */

public class ActivityActionManager {
    public static ActivityActionManager manager;
    private ActivityBean activityInfo;

    private ActivityActionManager() {
    }


    public static ActivityActionManager getInstance() {
        if (manager == null) {
            manager = new ActivityActionManager();
        }
        return manager;
    }



}
