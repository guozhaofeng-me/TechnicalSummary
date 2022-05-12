package utils;

import android.content.Context;

/**
 * User: Zephyr
 * Date: 2022/5/12
 * Time: 11:31
 * TODO APP签名检测，检测APP签名是否被篡改
 */
public class ApkSignChecker {
    private Context context;
    private String cer = null;
    private String realCer = null;

    public ApkSignChecker(Context context) {
        this.context = context;
    }


}
