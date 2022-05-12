package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * User: Zephyr
 * Date: 2022/5/12
 * Time: 11:18
 * json解析工具类，也可以使用GSON，或者封装GSON
 */
public class AndroidJsonUtils {
    public static JSONObject parseObject(String jsonStr) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONArray parseArray(String jsonStr) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static JSONObject getSubObject(JSONObject root, String nodeName) {
        JSONObject jsonObject = null;
        try {
            jsonObject = root.getJSONObject(nodeName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONArray getSubArray(JSONObject root, String nodeName) {
        JSONArray jsonArray = null;
        try {
            jsonArray = root.getJSONArray(nodeName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static JSONObject getSubObject(JSONArray array, int index) {
        JSONObject obj = null;
        try {
            obj = array.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
