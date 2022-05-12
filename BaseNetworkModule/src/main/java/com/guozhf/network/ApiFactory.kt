package com.guozhf.network

import com.guozhf.network.api.Api

/**
 * User: Zephyr
 * Date: 2022/5/10
 * Time: 10:37
 */
object ApiFactory {
    val api by lazy {
        ApiClient.getDefaultRetrofit().create(Api::class.java)
    }

    // api2
    // api3
}