package com.guozhf.technical

import com.guozhf.common.BaseApplication
import log.ZLog

class App : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        setDebug(BuildConfig.DEBUG) // 设置是DEBUG还是RELEASE模式

        //-----Log init-----
        ZLog.apply {
            setDebuggable(BuildConfig.DEBUG)
            setLogTag("TechnicalSummary")
        }

    }
}