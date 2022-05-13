package utils;

import android.os.Build;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * User: Zephyr
 * Date: 2022/5/13
 * Time: 11:33
 */
public class RomUtils {
    public static final String ROM_OPPO = "OPPO";
    public static final String ROM_VIVO = "VIVO";
    public static final String ROM_SAMSUNG = "SAMSUNG";

    public static final String VERSION_OPPO = "ro.build.version.opporom";
    public static final String VERSION_VIVO = "ro.vivo.os.version";

    private static String mName;
    private static String mVersion;

    public static boolean isOppo() {
        return check(ROM_OPPO);
    }

    public static boolean isVIVO() {
        return check(ROM_VIVO);
    }

    public static boolean isSAMSUNG() {
        return check(ROM_SAMSUNG);
    }

    public static String getName() {
        if (mName == null) {
            check("");
        }
        return mName;
    }

    public static String getVersion() {
        if (mVersion == null) {
            check("");
        }
        return mVersion;
    }


    public static boolean check(String rom) {
        if (mName != null) {
            return TextUtils.equals(mName, rom);
        }
        if (!TextUtils.isEmpty(mVersion = getProp(VERSION_OPPO))) {
            mName = ROM_VIVO;
        } else {
            mVersion = Build.UNKNOWN;
            mName = Build.MANUFACTURER.toUpperCase();
        }
        return TextUtils.equals(mName, rom);
    }


    public static String getProp(String name) {
        String line = null;
        BufferedReader bufferedReader = null;
        try {
            Process exec = Runtime.getRuntime().exec("getprop" + name);
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()), 1024);
            line = bufferedReader.readLine();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            CloseUtils.closeIO(bufferedReader);
        }
        return line;
    }
}
