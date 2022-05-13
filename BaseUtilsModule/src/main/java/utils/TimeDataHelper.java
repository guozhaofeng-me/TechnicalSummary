package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Zephyr
 * Date: 2022/5/13
 * Time: 11:51
 * 参考其他开源类
 */
public class TimeDataHelper {
    public static String getDateCN() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String date = format.format(new Date(System.currentTimeMillis()));
        return date; //
    }

    public static String getDateEN() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date(System.currentTimeMillis()));
        return date; //
    }

    /**
     * 返回年月日时间
     *
     * @param time    时间戳 the milliseconds since January 1, 1970, 00:00:00 GMT.See Also:
     *                System.currentTimeMillis()
     * @param pattern pattern为类似yyyyMMdd HH：mm：ss时间范式
     * @return
     */
    public static String getDateYMD(long time, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date(time);
        String date1 = format.format(date);
        return date1;
    }


    public static String getDateYMD(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        String date1 = format.format(date);
        return date1;
    }

    public static long getDateYMD(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            return -1;
        }
        return date.getTime();
    }

    public static String getCurrentDateYMD() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = format.format(new Date(System.currentTimeMillis()));
        return date1;
    }

    public static String getDateMDHMS(long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        Date date = new Date(time);
        String date1 = format.format(date);
        return date1;
    }

    public static String getDateEN(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        String date1 = format.format(date);
        return date1;
    }



}
