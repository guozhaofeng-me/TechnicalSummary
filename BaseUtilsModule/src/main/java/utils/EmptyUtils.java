package utils;

import android.os.Build;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;

import androidx.collection.SimpleArrayMap;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * User: Zephyr
 * Date: 2022/5/12
 * Time: 14:50
 */
public class EmptyUtils {

    /**
     * 判断对象是否为空
     * 这里把各种对象的空判断集合在了一起
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(final Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof String && obj.toString().length() == 0) {
            return true;
        }

        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }

        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }

        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }

        if (obj instanceof SimpleArrayMap && ((SimpleArrayMap) obj).isEmpty()) {
            return true;
        }

        if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0) {
            return true;
        }

        if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }

        if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0) {
            return true;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0) {
                return true;
            }
        }

        if (obj instanceof LongSparseArray && ((LongSparseArray<?>) obj).size() == 0) {
            return true;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (obj instanceof android.util.LongSparseArray && ((LongSparseArray) obj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象是否非空
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(final Object obj) {
        return !isEmpty(obj);
    }
}
