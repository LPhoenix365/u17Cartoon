package com.pingan.u17.manager;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.pingan.u17.base.U17Application;
import com.pingan.u17.bean.AddressBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/10/27
 */

public class AddressModelManager {
    public static AddressModelManager manager;

    private   AddressBean addressInfo;

    private AddressModelManager() {
        init();
    }

    public void init() {
        Gson gson = new Gson();
        try {
            InputStream  activitySteam = U17Application.getInstance().getAssets().open("json/all_city.json");
            JsonReader reader = new JsonReader(new InputStreamReader(activitySteam));
            reader.setLenient(true);
            addressInfo = gson.fromJson(reader, AddressBean.class);
            activitySteam.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AddressModelManager getInstance() {
        if (manager == null) {
            manager = new AddressModelManager();
        }
        return manager;
    }

    public AddressBean getAddressInfo() {
        return addressInfo;
    }
}
