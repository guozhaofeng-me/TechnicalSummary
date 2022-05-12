package utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * User: Zephyr
 * Date: 2022/5/12
 * Time: 14:39
 */
public class CloseUtils {
    /**
     * 关闭IO
     * 一般用在finally中
     * @param closeables
     */
    public static void closeIO(final Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
