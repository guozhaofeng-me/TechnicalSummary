package utils;

import java.util.regex.Pattern;

/**
 * User: Zephyr
 * Date: 2022/5/13
 * Time: 11:20
 */
public class MobileCheckHelper {

    /**
     * 验证是否是合规的手机号
     * 支持国际格式，+86135xxxx中国内地
     * +00852137xxxx...中国香港
     * @param mobile
     * @return
     */
    public static boolean checkMobile(String mobile) {
        String regex = null;
        if (mobile.length() == 13) {
            regex = "(\\+\\d+)?1[4]\\d{11}$";
        } else if (mobile.length() == 11) {
            if (mobile.substring(0, 2).equals("16")) {
                regex = "(\\+\\d+)?16[2567]\\d{8}$";
            } else if (mobile.substring(0, 2).equals("19")) {
                regex = "(\\+\\d+)?19[123589]\\d{8}$";
            } else {
                regex = "(\\+\\d+)?1[34578]\\d{9}$";
            }
        } else {
            regex = "(\\+\\d+)?1[34578]\\d{9}$";
        }
        return Pattern.matches(regex, mobile);
    }
}
