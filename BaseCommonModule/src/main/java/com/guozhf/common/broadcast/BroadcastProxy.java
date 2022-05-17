package com.guozhf.common.broadcast;

/**
 * User: Zephyr
 * Date: 2022/5/16
 * Time: 10:51
 */
public class BroadcastProxy {
    private static final BroadcastImpl instance = new BroadcastImpl();

    public static BroadcastImpl getInstance() {
        return instance;
    }

    private BroadcastProxy() {

    }

    protected void registerBroadcast(Object object) {
        instance.registerBroadcast(object);
    }

    protected void unregisterBroadcast(Object object) {
        instance.unregisterBroadcast(object);
    }

    protected void sendBroadcast(MessageEvent event) {
        instance.sendBroadcast(event);
    }

    protected void sendStickyBroadcast(MessageEvent event) {
        instance.sendStickyBroadcast(event);
    }
}
