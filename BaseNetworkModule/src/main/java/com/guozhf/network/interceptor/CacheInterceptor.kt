package com.guozhf.network.interceptor

import com.guozhf.common.BaseApplication
import okhttp3.CacheControl
import okhttp3.Request
import okhttp3.Response
import utils.NetworkUtils

/**
 * User: Zephyr
 * Date: 2022/5/12
 * Time: 10:13
 */
class CacheInterceptor : AbstractInterceptor() {
    override fun handleRequest(oldRequest: Request, builder: Request.Builder) {
        //网络不可用
        if (!NetworkUtils.isAvailable(BaseApplication.getContext())) {
            //在请求头中加入：强制使用缓存，不访问网络
            // 没有网络就使用缓存
            builder
                .cacheControl(CacheControl.FORCE_CACHE)
                .build();
        }
    }

    override fun handleResponse(oldResponse: Response, builder: Response.Builder) {
        // 有网络时 在响应头中加入：设置缓存超时时间0个小时
        // 也就是有网络的时候就不使用缓存
        if (!NetworkUtils.isAvailable(BaseApplication.getContext())) {
            val maxAge = 0
            builder
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("pragma")
                .build();
        } else {
            // 没有网络的时候使用缓存
            // 无网络时，在响应头中加入：设置超时为4周
            val maxStale = 60 * 60 * 24 * 28
            builder
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("pragma")
                .build();
        }
    }
}