package rst.framework.base.helper;

import java.util.List;

import rst.framework.interfaces.UnProguard;
import rst.framework.model.entity.BaseEntity;

/**
 * Created by iceman on 16/8/18 15:46
 * 邮箱：xubin865@pingan.com.cn
 */
public class HybridRouteTable implements UnProguard {


    public String appID;
    public String versionID;
    public String versionDate;
    /**
     * moduleID : 50100
     * moduleName : socialPay
     * moduleType : h5
     * moduleCName : 城乡居民医保缴费
     * moduleCity : [{"code":"all","url":"/app/socialSequirtyIndex","needLogin":true,"needBind":true,"needGesture":false,"needV2":false,"needV3":false,"isOpen":true}]
     */

    public List<ModulesEntity> modules;

    public static class ModulesEntity extends BaseEntity implements UnProguard{
        public int moduleID;
        public String moduleName;
        public String moduleType;
        public boolean needLogin;
        public boolean needBind;
        public String moduleCName;
        /**
         * code : all
         * url : /app/socialSequirtyIndex
         * needLogin : true
         * needBind : true
         * needGesture : false
         * needV2 : false
         * needV3 : false
         * isOpen : true
         */

        public List<ModuleCityEntity> moduleCity;

        public static class ModuleCityEntity extends BaseEntity implements UnProguard{
            public String code;
            public String url;
            public boolean needLogin;
            public boolean needBind;
            public boolean needGesture;
            public boolean needV2;
            public boolean needV3;
            public boolean isOpen;
        }
    }
}
