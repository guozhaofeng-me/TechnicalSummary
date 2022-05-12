package utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


/**
 * User: Zephyr
 * Date: 2022/5/12
 * Time: 16:48
 */
public class JSONUtils {
    private static Gson sGson = new Gson();

    public static String toJson(Object obj) {
        return sGson.toJson(obj);
    }

    public static <T> T parseJson(String json, Class<T> clazz) {
        return sGson.fromJson(json, clazz);
    }

    public static <T> T parseJson(String json) {
        return sGson.fromJson(json, new TypeToken<T>() {
        }.getType());
    }

    public static JsonElement getJsonSubObject(JsonElement element, String name) {
        if (element == null) return null;
        JsonObject obj = element.getAsJsonObject();
        if (obj == null) return null;
        JsonElement jsonElement = obj.get(name);
        if (jsonElement == null) return null;
        return jsonElement;
    }

    public static JsonArray getJsonSubArray(JsonElement element, String name) {
        if (element == null) return null;
        JsonObject obj = element.getAsJsonObject();
        if (obj == null) return null;
        JsonElement sub = obj.get(name);
        if (sub == null) return null;
        return sub.getAsJsonArray();
    }
}
