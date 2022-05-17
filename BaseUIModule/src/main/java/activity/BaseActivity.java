package activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.guozhf.baseuimodule.R;
import com.guozhf.common.base.ActivityManager;
import com.guozhf.common.broadcast.BroadcastProxy;
import com.guozhf.common.broadcast.MessageEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * User: Zephyr
 * Date: 2022/5/16
 * Time: 10:07
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void setContentView();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    protected boolean isUseBroadcast() {
        return false;
    }


    protected void releaseOnDestroy() {

    }

    protected void releaseOnStop() {

    }

    protected void releaseOnPause() {

    }

    protected boolean needPendingTransition() {
        return true;
    }

    protected void initStatusBar() {

    }

    protected Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!needPendingTransition()) {
            overridePendingTransition(R.anim.anim_activity_left_in, R.anim.anim_activity_left_out);
        }
        context = this;
        ActivityManager.getInstance().addActivityToTask(this);
        setContentView();
        initStatusBar();
        initView();
        initData();
        initListener();
        if (isUseBroadcast()) {
            // 注册广播，实际上是使用Eventbus
            BroadcastProxy.getInstance().registerBroadcast(this);
        }
//        registerBroadCast(); 这里是原生的广播注册
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unRegisterBoradcast(); 原生广播的注销
        releaseOnDestroy();
        BroadcastProxy.getInstance().unregisterBroadcast(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseOnPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseOnStop();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (true) {
            onNightModeChanged();
        }
    }

    protected void onNightModeChanged() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent event) {
        onMessageCallback(event);
    }

    /**
     * 处理Eventbus的回调，需要重写
     *
     * @param event
     */
    protected void onMessageCallback(MessageEvent event) {

    }

    protected void sendMessage(MessageEvent event) {
        BroadcastProxy.getInstance().sendBroadcast(event);
    }

    protected void sendStickyMessage(MessageEvent event) {
        BroadcastProxy.getInstance().sendStickyBroadcast(event);
    }

    @Override
    public void finish() {
        super.finish();
        if (!needPendingTransition())
    }
}
