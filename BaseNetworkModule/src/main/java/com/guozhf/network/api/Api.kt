package com.guozhf.network.api

import com.guozhf.network.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * User: Zephyr
 * Date: 2022/5/7
 * Time: 17:40
 * Retrofit协议类
 */
interface Api {
    @GET("/abc/def/{param1}")
    fun testGet(@Query("param1") p1: String): Call<BaseResponse<List<String>>> // BaseResponse为和后端协定的协议，List<String>为响应中的data
}