package com.guozhf.common.broadcast;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Zephyr
 * Date: 2022/5/16
 * Time: 10:43
 */
public class MessageEvent {
    private Map<String, Object> msgEventMap = new HashMap<>();

    private Bundle bundle = new Bundle();

    public boolean containsKey(String key) {
        return bundle.containsKey(key) || msgEventMap.containsKey(key);
    }

    public void putString(String key, String value) {
        bundle.putString(key, value);
    }

    public String getString(String key) {
        return bundle.getString(key);
    }

    public void putInt(String key, int value) {
        bundle.putInt(key, value);
    }

    public int getInt(String key) {
        return bundle.getInt(key, -1);
    }

    public void putBoolean(String key, boolean value) {
        bundle.putBoolean(key, value);
    }

    public boolean getBoolean(String key) {
        return bundle.getBoolean(key, false);
    }

    public void putObject(String key, Object object) {
        msgEventMap.put(key, object);
    }

    public Object getObjectValue(String key) {
        return msgEventMap.get(key);
    }

}
