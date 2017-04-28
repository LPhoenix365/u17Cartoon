package rst.framework.base.helper;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import rst.framework.BaseApplication;
import rst.framework.FrameWorkConfig;
import rst.framework.hybird.ModuleResourceManager;
import rst.framework.hybird.WebViewModel;
import rst.framework.model.viewmodel.BaseViewModel;

/**
 * hybrid模块跳转管理.包含向h5和native跳转
 */
public class HybridRouteManager {
    private static HybridRouteManager activityInfoConfig;
    private HybridRouteTable hybridRouteTable;
    private ActivityConfigTable activityConfigTable;

    private HashMap<String, HybridRoute> routeCache = new HashMap<>();

    private HybridRouteManager() {

    }

    public void init() {
        ModuleResourceManager.getInstance().decompressPreInstalledModule();
        Gson gson = new Gson();
        try {
            InputStream hybridConfigStream = BaseApplication.getAppContext().getAssets().open("ncapp.router.json");
            JsonReader reader = new JsonReader(new InputStreamReader(hybridConfigStream));
            reader.setLenient(true);
            hybridRouteTable = gson.fromJson(reader, HybridRouteTable.class);

            InputStream activityConfigStream = BaseApplication.getAppContext().getAssets().open("activity_config.json");
            reader = new JsonReader(new InputStreamReader(activityConfigStream));
            reader.setLenient(true);
            activityConfigTable = new Gson().fromJson(reader, ActivityConfigTable.class);

            hybridConfigStream.close();
            activityConfigStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HybridRouteManager getInstance() {
        if (activityInfoConfig == null) {
            activityInfoConfig = new HybridRouteManager();
        }
        return activityInfoConfig;
    }

    /**
     * 根据配置id寻找对应的路由
     *
     * @param id
     * @return
     */
    public HybridRoute getHybridRoute(int id) {
        String zonecode = FrameWorkConfig.frameworkSupport.getZoneCode();
        HybridRoute route = routeCache.get(zonecode + id);
        if (route != null) {
            return route;
        }
        if ((id > 10000 && id < 50000)) {//1xxxx五位数为native模块,否则为h5模块
            ActivityConfigTable.ActivityEntity activityEntity = findNativeModule(id);
            if (activityEntity != null) {
                route = new HybridRoute();
                route.moduleType = HybridRoute.ROUTE_TYPE_NATIVE;
                route.moduleName = activityEntity.moduleName;
                route.moduleCName = activityEntity.moduleCName;
                route.needLogin = activityEntity.needLogin;
                route.needBind = activityEntity.needBind;
                try {
                    route.pageClass = Class.forName(activityEntity.pageClass);
                    route.viewModel = (BaseViewModel) Class.forName(activityEntity.viewModel).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                ActivityConfigTable.ActivityEntity.ModuleCityEntity matchCity = findRouteEntitybyZonecode(activityEntity, zonecode);
                if (matchCity != null) {
                    route.needLogin = matchCity.needLogin;
                    route.needBind = matchCity.needBind;
                    route.needGesture = matchCity.needGesture;
                    route.needV2 = matchCity.needV2;
                    route.needV3 = matchCity.needV3;
                }

            }
        } else {
            HybridRouteTable.ModulesEntity modulesEntity = findH5Module(id);

            ActivityConfigTable.ActivityEntity activityEntity = findNativeModule(10000);
            if (modulesEntity != null) {
                route = new HybridRoute();
                route.moduleType = HybridRoute.ROUTE_TYPE_H5;
                route.moduleName = modulesEntity.moduleName;
                route.moduleCName = modulesEntity.moduleCName;
                route.needLogin = modulesEntity.needLogin;
                route.needBind = modulesEntity.needBind;

                try {
                    route.pageClass = Class.forName(activityEntity.pageClass);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                String url = null;
                HybridRouteTable.ModulesEntity.ModuleCityEntity matchCity = findRouteEntitybyZonecode(modulesEntity, zonecode);
                if (matchCity != null) {
                    url = route.moduleName + "/index.html#" + matchCity.url;
                    route.needLogin = matchCity.needLogin;
                    route.needBind = matchCity.needBind;
                    route.needGesture = matchCity.needGesture;
                    route.needV2 = matchCity.needV2;
                    route.needV3 = matchCity.needV3;
                }
                route.viewModel = new WebViewModel(WebViewModel.Type.MODULE, url);

            }
        }
        return route;
    }

    private HybridRouteTable.ModulesEntity findH5Module(int id) {
        for (HybridRouteTable.ModulesEntity modulesEntity : hybridRouteTable.modules) {
            if (modulesEntity.moduleID == id) {
                return modulesEntity;
            }
        }
        return null;
    }

    private ActivityConfigTable.ActivityEntity findNativeModule(int id) {
        for (ActivityConfigTable.ActivityEntity activityEntity : activityConfigTable.activity) {
            if (activityEntity.activityId == id) {
                return activityEntity;
            }
        }
        return null;
    }

    /**
     * 根据指定的zonecode从module中找到对应的路由
     *
     * @param modulesEntity
     * @param zonecode
     * @return
     */
    private HybridRouteTable.ModulesEntity.ModuleCityEntity findRouteEntitybyZonecode(HybridRouteTable.ModulesEntity modulesEntity, String zonecode) {
        if (modulesEntity.moduleCity.size() == 1) {//如果只配置了一个城市,直接用...让h5慢慢改去吧.
            return modulesEntity.moduleCity.get(0);
        }
        HybridRouteTable.ModulesEntity.ModuleCityEntity allCityEntity = null;
        for (HybridRouteTable.ModulesEntity.ModuleCityEntity cityEntity : modulesEntity.moduleCity) {
            if (cityEntity.code.equals(zonecode)) {
                return cityEntity;
            }
            if (cityEntity.code.equals("all")) {
                allCityEntity = cityEntity;
            }
        }
        return allCityEntity;

    }

    /**
     * 根据指定的zonecode从module中找到对应的路由
     *
     * @param modulesEntity
     * @param zonecode
     * @return
     */
    private ActivityConfigTable.ActivityEntity.ModuleCityEntity findRouteEntitybyZonecode(ActivityConfigTable.ActivityEntity modulesEntity, String zonecode) {
        if (modulesEntity.moduleCity == null || modulesEntity.moduleCity.size() == 0) {
            return null;
        }
        if (modulesEntity.moduleCity.size() == 1) {
            return modulesEntity.moduleCity.get(0);
        }
        ActivityConfigTable.ActivityEntity.ModuleCityEntity allCityEntity = null;
        for (ActivityConfigTable.ActivityEntity.ModuleCityEntity cityEntity : modulesEntity.moduleCity) {
            if (cityEntity.code.equals(zonecode)) {
                return cityEntity;
            }
            if (cityEntity.code.equals("all")) {
                allCityEntity = cityEntity;
            }
        }
        return allCityEntity;

    }


}
