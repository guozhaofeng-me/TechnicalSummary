package utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * User: Zephyr
 * Date: 2022/5/13
 * Time: 14:49
 */
public class VersionHelper {

    public static String getVersionName(Context context) {
        String code = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            code = packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    public static String getVersionNameWithV(Context context) {
        String code = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            code = "V" + packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    public static int getVersionCode(Context context) {
        int code = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            code = packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

}
