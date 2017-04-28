package rst.framework;

import com.raizlabs.android.dbflow.config.DatabaseHolder;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by iceman on 16/12/5.
 */

public class FrameworkModule {


    public static class ModuleIniter {
        /**
         * 用于dbflow初试化
         * library module中若用到dbflow,需要在build.gradle中定义module name,然后在FlowManager init时加入自定义的databaseholder
         * 如果此module中没有用到数据库.则返回null即可
         *
         * @return
         */
        public static Class<? extends DatabaseHolder> getDatabaseHolderClass() {
            try {
                return (Class<? extends DatabaseHolder>) Class.forName(FlowManager.class.getPackage().getName() + ".FrameWorkGeneratedDatabaseHolder");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
