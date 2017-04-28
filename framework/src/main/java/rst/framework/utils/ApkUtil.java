package rst.framework.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by iceman on 16/4/26 17:10
 * 邮箱：xubin865@pingan.com.cn
 */
public class ApkUtil {
    public static String getAppVersionName(Context context) {
        String packageName = context.getPackageName();
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getAppVersionCode(Context context) {
        String packageName = context.getPackageName();
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
