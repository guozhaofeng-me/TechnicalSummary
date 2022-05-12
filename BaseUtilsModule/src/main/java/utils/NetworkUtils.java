package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * User: Zephyr
 * Date: 2022/5/12
 * Time: 9:50
 */
public class NetworkUtils {
    /**
     * 获取活动网路信息
     *
     * @param context 上下文
     * @return NetworkInfo
     * permissions required by ConnectivityManager.getActiveNetworkInfo: android.permission.ACCESS_NETWORK_STATE
     */
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * 判断网络是否可用
     * <p>需添加权限 android.permission.ACCESS_NETWORK_STATE</p>
     */
    public static boolean isAvailable(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isAvailable();
    }

    /**
     * 是否是wifi连接
     *
     * @param context
     * @return permissions required by ConnectivityManager.getActiveNetworkInfo: android.permission.ACCESS_NETWORK_STATE
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return networkInfo.isConnected();
    }

    /**
     * 是否打开了wifi
     *
     * @param context
     * @return
     */
    public static boolean isWifiEnabled(Context context) {
        NetworkInfo networkInfo = getActiveNetworkInfo(context);
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 是否打开了移动数据开关
     *
     * @param context
     * @return
     */
    public static boolean isMobileEnabled(Context context) {
        NetworkInfo networkInfo = getActiveNetworkInfo(context);
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * 获取IP地址
     * @param useIPV4
     * @return
     */
    public static String getIPAddress(final boolean useIPV4) {
        try {
            for (Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces(); nis != null && nis.hasMoreElements(); ) {
                NetworkInterface ni = nis.nextElement();
                // 防止小米手机返回10.0.2.15
                if (!ni.isUp()) continue;
                for (Enumeration<InetAddress> address = ni.getInetAddresses(); address.hasMoreElements(); ) {
                    InetAddress inetAddress = address.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        boolean isIPV4 = hostAddress.indexOf(":") < 0;
                        if (useIPV4) {
                            if (isIPV4) return hostAddress;
                        } else {
                            if (!isIPV4) {
                                int index = hostAddress.indexOf("%");
                                return index < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, index).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
