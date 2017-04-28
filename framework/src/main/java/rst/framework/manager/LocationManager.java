package rst.framework.manager;

import android.text.TextUtils;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.util.List;

import rst.framework.BaseApplication;
import rst.framework.FrameWorkConfig;
import rst.framework.model.entity.LocationEntity;
import rst.framework.model.viewmodel.LocationViewModel;
import rst.framework.session.Session;
import rst.framework.utils.LogUtil;

/**
 * @Description 开启地理定位并根据定位信息找到开发城市的zoneCode
 * @Author louyaming802
 * @Date 16/11/21
 * @Version 2.10.X
 */

public class LocationManager implements AMapLocationListener {
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    private static LocationManager locationInstance;

    private LocationEntity locationEntity = null;

    private List<LocationViewModel> openCityList = null;

    public String zoneCode = "0086";
    public String zoneName = "";
    public String areaCode = "";
    public String areaName = "";

    public static LocationManager getLocationInstance(){
        if (null == locationInstance){
            locationInstance = new LocationManager();
        }

        return locationInstance;
    }

    /**
     * 初始化开放城市列表
     */
    private void initOpenCityList(){
        if (null == openCityList || openCityList.size()<1){
            openCityList = FrameWorkConfig.frameworkSupport.getOpenCityList();
        }
    }

    /**
     * 启动定位服务
     */
    public void startLocation() {
        if (null == locationClient){
            locationClient = new AMapLocationClient(BaseApplication.getAppContext());
            locationOption = new AMapLocationClientOption();
            // 设置定位模式为高精度模式
            locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            // 设置定位监听
            locationClient.setLocationListener(this);
            // 设置定位参数
            locationClient.setLocationOption(locationOption);
            locationOption.setGpsFirst(true);
        }

        if (null==locationEntity){
            locationEntity = new LocationEntity();
        }

        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位服务
     */
    public void stopLocation(){
        if (null != locationClient){
            locationClient.stopLocation();
        }
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {

        if (null==amapLocation) return;

        if (amapLocation.getErrorCode() == 0) {
            locationEntity.setLatitude(amapLocation.getLatitude());
            locationEntity.setLongitude(amapLocation.getLongitude());

            locationEntity.setAddress(amapLocation.getAddress());
            locationEntity.setCountry(amapLocation.getCountry());
            locationEntity.setProvince(amapLocation.getProvince());
            locationEntity.setCity(amapLocation.getCity());
            locationEntity.setDistrict(amapLocation.getDistrict());
            locationEntity.setStreet(amapLocation.getStreet());
            locationEntity.setStreetNum(amapLocation.getStreetNum());
            locationEntity.setCityCode(amapLocation.getCityCode());
            locationEntity.setAdCode(amapLocation.getAdCode());
            locationEntity.setLocationType(amapLocation.getLocationType());

            stopLocation();

            updateLocationInfo();
        }else{
            LogUtil.i("AmapError","location Error, ErrCode:" + amapLocation.getErrorCode() + ", errInfo:" + amapLocation.getErrorInfo());
        }

    }

    /**
     * 更新环境的Location信息，如zoneCode,zoneName
     */
    public void updateLocationInfo(){
        initOpenCityList();

        for (LocationViewModel viewModel: openCityList){
            if (viewModel.getCityCode().equals(locationEntity.getCityCode()) || viewModel.getCityCode().equals(locationEntity.getAdCode())){
                zoneCode = viewModel.getZoneCode();
                zoneName = viewModel.getZoneName();

                if (null!=viewModel.getRegions() && viewModel.getRegions().size()>0){
                    for(LocationViewModel.RegionsBean bean:viewModel.getRegions()){
                        if (bean.getDistrictCode().equals(locationEntity.getAdCode())){
                            areaCode = bean.getAreaCode();
                            areaName = bean.getAreaName();

                            break;
                        }else{
                            continue;
                        }
                    }
                }

                break;
            }else{
                continue;
            }
        }
    }

    /**
     * 根据zoneCode或者areaCode
     * @param zoneCode  如果为空，则不执行该方法
     * @param areaCode
     */
    public void updateLocationInfo(String zoneCode,String areaCode){
        if (TextUtils.isEmpty(zoneCode))    return;

        this.zoneCode = zoneCode;

        initOpenCityList();

        for (LocationViewModel viewModel: openCityList){
            if (viewModel.getZoneCode().equals(zoneCode)){
                zoneName = viewModel.getZoneName();

                if (TextUtils.isEmpty(areaCode))    break;
                this.areaCode = areaCode;

                if (null!=viewModel.getRegions() && viewModel.getRegions().size()>0){
                    for(LocationViewModel.RegionsBean bean:viewModel.getRegions()){
                        if (bean.getAreaCode().equals(areaCode)){
                            areaName = bean.getAreaName();

                            break;
                        }else{
                            continue;
                        }
                    }
                }

                break;
            }else{
                continue;
            }
        }
    }

    /**
     * 更新统筹区信息
     * @param areaCode
     * @param areaName
     */
    public void updateAreaInfo(String areaCode,String areaName){
        this.areaCode = areaCode;
        this.areaName = areaName;
    }

    /**
     * 返回高德定位的LocationEntity，包括所有的定位信息
     *
     * @return
     */
    public LocationEntity getLocationEntity(){
        if (null!=locationEntity) {
            return locationEntity;
        }else{
            return new LocationEntity();
        }
    }

    /**
     * 获取app内使用的zoneCode,优先使用绑卡城市,其次使用定位城市
     *
     * @return
     */
    public String getLogicZoneCode() {
        String userZoneCode = Session.getZoneCode();
        if (TextUtils.isEmpty(userZoneCode)) {
            if (!TextUtils.isEmpty(zoneCode)) {
                return zoneCode;
            } else {
                return "0086";
            }
        } else {
            return userZoneCode;
        }

    }


}
