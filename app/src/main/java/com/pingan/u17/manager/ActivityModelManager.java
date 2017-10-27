package com.pingan.u17.manager;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.pingan.u17.base.U17Application;
import com.pingan.u17.bean.ActivityBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/10/27
 */

public class ActivityModelManager {
    public static ActivityModelManager manager;
    private  ActivityBean activityInfo;

    private ActivityModelManager() {

    }

    public void init() {
        Gson gson = new Gson();
        try {
            InputStream  activitySteam = U17Application.getInstance().getAssets().open("json/u17.router.json");
            JsonReader reader = new JsonReader(new InputStreamReader(activitySteam));
            reader.setLenient(true);
            activityInfo = gson.fromJson(reader, ActivityBean.class);
            activitySteam.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ActivityModelManager getInstance() {
        if (manager == null) {
            manager = new ActivityModelManager();
        }
        return manager;
    }




}
