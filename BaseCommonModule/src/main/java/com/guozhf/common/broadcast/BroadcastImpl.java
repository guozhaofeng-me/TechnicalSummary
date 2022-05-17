package com.guozhf.common.broadcast;

import org.greenrobot.eventbus.EventBus;

/**
 * User: Zephyr
 * Date: 2022/5/16
 * Time: 10:49
 */
public class BroadcastImpl implements IBroadcast{
    @Override
    public void registerBroadcast(Object object) {
        if (!EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().register(object);
        }
    }

    @Override
    public void unregisterBroadcast(Object object) {
        if (EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().unregister(object);
        }

    }

    @Override
    public void sendBroadcast(MessageEvent event) {
        EventBus.getDefault().post(event);
    }

    @Override
    public void sendStickyBroadcast(MessageEvent event) {
        EventBus.getDefault().postSticky(event);
    }
}
