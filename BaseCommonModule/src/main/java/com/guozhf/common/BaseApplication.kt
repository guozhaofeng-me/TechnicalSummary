package com.guozhf.common

import android.app.Application
import android.content.Context
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
}