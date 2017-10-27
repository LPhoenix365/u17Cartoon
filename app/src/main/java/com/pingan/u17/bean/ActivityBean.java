package com.pingan.u17.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/10/27
 */

public class ActivityBean implements Serializable {

    /*
    {
        "appID": "",
            "versionID": "",
            "versionDate": "",
            "modules": [
        {
            "moduleID": 10010,
                "moduleName": "CartoonDetail",
                "moduleType": "native",
                "pageClass": ".ui.activity.CartoonDetailActivity",
                "moduleCName": "漫画详情",
                "moduleContent": [

            ]
        }
    ]
    }*/
    public String appID;
    public String versionID;
    public String versionDate;
    public List<moduleBean> modules;

    public static class moduleBean {
        public String moduleID;
        public String moduleName;
        public String moduleType;
        public String pageClass;
        public String moduleCName;
        public List<Object> moduleContent;
    }

}
