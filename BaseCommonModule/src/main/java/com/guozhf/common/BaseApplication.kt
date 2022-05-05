package com.guozhf.common

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

open class BaseApplication : Application() {
    private var isDebug: Boolean = false

    override fun onCreate() {
        super.onCreate()
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

    fun setDebug(isDebug: Boolean) {
        this.isDebug = isDebug
    }
}