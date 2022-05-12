package utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * User: Zephyr
 * Date: 2022/5/12
 * Time: 14:31
 */
public class BrightnessUtils {
    public static final int DEFAULT_BRIGHT_LEVEL_COUNT = 25; // 0-24
    public static final int DEFAULT_BRIGHT_LEVEL_MAX = 24; // 0- 24
    public static final int DEFAULT_BRIGHT_LEVEL_MIN = 0; // 0 -24
    public static final int DEFAULT_BRIGHT_LEVEL = 12;

    /**
     * 获取系统默认屏幕亮度值 屏幕亮度值返回（0-255）
     *
     * @param context
     * @return
     */
    public static int getScreenBrightness(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        int defVal = 127;
        return Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, defVal);
    }

    public static int getScreenBrightnessLevel(Context context) {
        int brightness = getScreenBrightness(context);
        return (int) (brightness / 255 * DEFAULT_BRIGHT_LEVEL_MAX);
    }

    /**
     * 设置APP界面屏幕亮度
     *
     * @param activity
     * @param brightnessValue
     */
    public static void setAppScreenBrightness(Activity activity, float brightnessValue) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.screenBrightness = brightnessValue / 255.0f;
        Log.d("TAG", "setAppScreenBrightness: " + lp.screenBrightness);
        window.setAttributes(lp);
    }

    public static void setAppScreenBrightnessLevel(Activity activity, int brightnessLevel) {
        float brightnessValue = (float) brightnessLevel / DEFAULT_BRIGHT_LEVEL_MAX * 255f;
        brightnessValue = brightnessValue == 0 ? 1 : brightnessValue;
        setAppScreenBrightness(activity, brightnessValue);
    }
}
