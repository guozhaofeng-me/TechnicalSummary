package com.guozhf.network.interceptor

import okhttp3.Request
import okhttp3.Response

/**
 * User: Zephyr
 * Date: 2022/5/7
 * Time: 16:30
 */
class CommonInterceptor : AbstractInterceptor() {
    override fun handleRequest(oldRequest: Request, builder: Request.Builder) {

    }

    override fun handleResponse(oldResponse: Response, builder: Response.Builder) {
    }
}