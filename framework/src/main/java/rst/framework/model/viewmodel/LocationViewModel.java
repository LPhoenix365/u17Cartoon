package rst.framework.model.viewmodel;

import java.util.List;

/**
 * @Description     开发城市列表对应的ViewModel
 * @Author louyaming802
 * @Date 16/12/19
 * @Version 2.10.X
 */

public class LocationViewModel extends BaseViewModel {

    /**
     * zoneName : 绍兴
     * regions : [{"districtCode":"330600","presumed":"true","areaName":"市本级","areaCode":"330699"},{"districtCode":"330603","areaName":"柯桥","areaCode":"330621","presumed":"false"},{"districtCode":"330604","areaName":"上虞","presumed":"false","areaCode":"330682"},{"areaName":"诸暨","areaCode":"330681","presumed":"false","districtCode":"330681"},{"areaName":"嵊州","presumed":"false","districtCode":"330683","areaCode":"330683"},{"presumed":"false","areaCode":"330624","districtCode":"330624","areaName":"新昌"}]
     * zoneCode : 0575
     * tipList : []
     * spell : shaoxing
     * aleph : S
     * cityCode : 0575
     */

    private String zoneName;
    private String zoneCode;
    private String spell;
    private String aleph;
    private String cityCode;
    /**
     * districtCode : 330600
     * presumed : true
     * areaName : 市本级
     * areaCode : 330699
     */

    private List<RegionsBean> regions;
    private List<?> tipList;

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getAleph() {
        return aleph;
    }

    public void setAleph(String aleph) {
        this.aleph = aleph;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public List<RegionsBean> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionsBean> regions) {
        this.regions = regions;
    }

    public List<?> getTipList() {
        return tipList;
    }

    public void setTipList(List<?> tipList) {
        this.tipList = tipList;
    }

    public static class RegionsBean {
        private String districtCode;
        private String presumed;
        private String areaName;
        private String areaCode;

        public String getDistrictCode() {
            return districtCode;
        }

        public void setDistrictCode(String districtCode) {
            this.districtCode = districtCode;
        }

        public String getPresumed() {
            return presumed;
        }

        public void setPresumed(String presumed) {
            this.presumed = presumed;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }
    }
}
