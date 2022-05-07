package com.guozhf.technical

import com.guozhf.common.BaseApplication
import com.guozhf.network.ApiClient
import com.guozhf.network.ApiClientConfig
import com.guozhf.network.api.Api
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

        //----Network init----
        // 1. 默认配置
        ApiClient.setDefaultBaseUrl("http://www.taobao.com") // 设置baseurl，对于只有单个baseurl的应用，可以在Application调用此方法设置一个baseurl
        val defaultRetrofit =
            ApiClient.getDefaultRetrofit() // 单例对象，使用的时候调用，或者在Application或单例对象中调用都可以
        val api = defaultRetrofit.create(Api::class.java) // 通过接口协议创建接口对象，使用时调用，或者放在一个单例对象中

        // 2. 自定义配置
        val config = ApiClientConfig.create()
            .setBaseUrl("http://www.baidu.com")
            .setWriteTimeout(20000)
            .setReadTimeout(20000)
            .setConnectTimeout(20000)
            .setCallTimeout(20000)
        val retrofit = ApiClient.getRetrofit(config)
        val api2 = retrofit.create(Api::class.java)
    }
}