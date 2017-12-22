package com.pingan.u17.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/12/22
 */

public class AddressBean implements Serializable {

    public List<Address> address;
    public static class Address implements Serializable{
        public ProvinceBean sysAreaCity;
        public List<CityBean> list;

        public static class ProvinceBean {
            public String areaId;
            public String id;
            public String name;
            public String parentId;
            public String pinyin;
        }

        public static class CityBean {
            public SysAreaCityBean sysAreaCity;
            public List<AreaBean> list;

            public static class SysAreaCityBean {
                public String areaId;
                public String id;
                public String name;
                public String parentId;
                public String pinyin;
            }

            public static class AreaBean {
                public String areaId;
                public String id;
                public String name;
                public String parentId;
            }
        }
    }

}
