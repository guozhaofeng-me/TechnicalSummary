package utils;

import android.text.TextUtils;
import android.util.Base64;

/**
 * User: Zephyr
 * Date: 2022/5/12
 * Time: 14:23
 */
public class Base64Helper {
    public static String encode(byte[] bytes) {
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public static String encodeNoWrap(byte[] bytes) {
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    public static byte[] decode(String str) {
        return Base64.decode(str, Base64.DEFAULT);
    }

    public static byte[] decodeToBytesByBase64(String str) {
        byte[] result = null;
        try {
            if (!TextUtils.isEmpty(str)) {
                byte[] codes = Base64.decode(str.replaceAll(" ", "+"), Base64.DEFAULT);
                result = codes;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
