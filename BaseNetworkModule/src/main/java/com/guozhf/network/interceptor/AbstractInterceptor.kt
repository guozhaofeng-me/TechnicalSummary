package com.guozhf.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * User: Zephyr
 * Date: 2022/5/7
 * Time: 16:59
 */
abstract class AbstractInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequestBuilder = request.newBuilder()
        // 加一些请求逻辑
        handleRequest(request, newRequestBuilder)
        val newRequest = newRequestBuilder.build()

        /*********/
        val response = chain.proceed(newRequest)

        /*********/
        val newResponseBuilder = response.newBuilder()
        // 加一些响应逻辑
        handleResponse(response, newResponseBuilder)
        val newResponse = newResponseBuilder.build()
        return newResponse
    }
    abstract fun handleRequest(oldRequest: Request, builder: Request.Builder)
    abstract fun handleResponse(oldResponse: Response, builder: Response.Builder)
}