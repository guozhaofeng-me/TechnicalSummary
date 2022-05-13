package com.guozhf.common

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.text.TextUtils
import com.alibaba.android.arouter.launcher.ARouter

open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
        initARouter()
    }

    // 初始化ARouter
    private fun initARouter() {
        if (isDebug) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
    }

    companion object {
        private var appContext: Context? = null
        var isDebug: Boolean = false
        fun getContext():Context? = appContext
    }

    fun isCurrentProcessName(context: Context): Boolean {
        val pid = android.os.Process.myPid()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningAppProcesses = activityManager.runningAppProcesses ?: return false
        for (appProcess in runningAppProcesses) {
            if (appProcess != null && appProcess.pid == pid) {
                return TextUtils.equals(packageName, appProcess.processName)
            }
        }
        return false
    }
}