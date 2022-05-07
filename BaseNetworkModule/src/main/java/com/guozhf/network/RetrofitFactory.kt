package com.guozhf.network

import com.guozhf.network.api.Api

/**
 * User: Zephyr
 * Date: 2022/5/7
 * Time: 17:50
 */
object RetrofitFactory {

    // 入参是url， Api协议
    fun test1() {
        ApiClient.setDefaultBaseUrl("http://www.taobao.com") // 设置baseurl，对于只有单个baseurl的应用，可以在Application调用此方法设置一个baseurl
        val defaultRetrofit =
            ApiClient.getDefaultRetrofit() // 单例对象，使用的时候调用，或者在Application或单例对象中调用都可以
        val api = defaultRetrofit.create(Api::class.java) // 通过接口协议创建接口对象，使用时调用，或者放在一个单例对象中
    }

    fun test2() {
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