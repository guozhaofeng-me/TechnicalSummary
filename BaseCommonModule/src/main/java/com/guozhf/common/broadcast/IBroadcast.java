package com.guozhf.common.broadcast;

/**
 * User: Zephyr
 * Date: 2022/5/16
 * Time: 10:29
 */
public interface IBroadcast {
    /**
     * 注册广播
     * @param object
     */
    void registerBroadcast(Object object);

    /**
     * 注销广播
     * @param object
     */
    void unregisterBroadcast(Object object);

    /**
     * 发送广播
     * @param event
     */
    void sendBroadcast(MessageEvent event);

    /**
     * 发送粘性广播
     * @param event
     */
    void sendStickyBroadcast(MessageEvent event);
}

