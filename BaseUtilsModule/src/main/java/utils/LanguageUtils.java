package utils;

import java.util.Locale;

/**
 * User: Zephyr
 * Date: 2022/5/12
 * Time: 17:13
 */
public class LanguageUtils {
    public static boolean isChinese() {
        String language = Locale.getDefault().getLanguage();
        return language.equals(Locale.CHINESE.toString())
                || language.equals(Locale.CHINA.toString())
                || language.equals(Locale.SIMPLIFIED_CHINESE.toString())
                || language.equals(Locale.TRADITIONAL_CHINESE.toString());
    }

    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }
}
