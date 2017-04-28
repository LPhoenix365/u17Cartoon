package rst.framework.base.helper;

import java.util.List;

import rst.framework.interfaces.UnProguard;
import rst.framework.model.entity.BaseEntity;

/**
 * activity配置
 */
public class ActivityConfigTable implements UnProguard {


    /**
     * activityId : 36
     * moduleName : nearByHospital
     * moduleCName : 医疗机构
     * viewModel : com.paic.mhis.ncms.map.NearByViewModel
     * pageClass : com.paic.mhis.ncms.map.NearByHospitalActivity
     * moduleCity : [{"code":"all","needLogin":false,"needBind":false},{"code":"110106","needLogin":false,"needBind":false}]
     */

    public List<ActivityEntity> activity;

    public static class ActivityEntity extends BaseEntity implements UnProguard{
        public int activityId;
        public String moduleName;
        public String moduleCName;
        public String viewModel;
        public boolean needLogin;
        public boolean needBind;
        public String pageClass;

        /**
         * code : all
         * needLogin : false
         * needBind : false
         */

        public List<ModuleCityEntity> moduleCity;

        public static class ModuleCityEntity extends BaseEntity implements UnProguard{
            public String code;
            public boolean needLogin;
            public boolean needBind;
            public boolean needGesture;
            public boolean needV2;
            public boolean needV3;
        }
    }
}
