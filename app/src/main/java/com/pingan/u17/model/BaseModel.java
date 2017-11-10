package com.pingan.u17.model;

import com.pingan.u17.base.U17Application;
import com.pingan.u17.net.RestApi;


/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public class BaseModel {

    public RestApi api;

    public BaseModel() {
        api = U17Application.getInstance().getHttpClient().getApiService();
    }


}
