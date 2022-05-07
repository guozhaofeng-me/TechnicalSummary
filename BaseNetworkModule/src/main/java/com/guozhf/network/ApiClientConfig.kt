package com.guozhf.network

import com.guozhf.network.interceptor.CommonInterceptor
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.IllegalArgumentException

/**
 * User: Zephyr
 * Date: 2022/5/6
 * Time: 16:13
 * Retrofit的公用数据
 * 在Retrofit初始化前调用
 */
class ApiClientConfig {

    companion object {
        internal const val DEFAULT_CALL_TIMEOUT = 10000L
        internal const val DEFAULT_CONNECT_TIMEOUT = 10000L
        internal const val DEFAULT_READ_TIMEOUT = 10000L
        internal const val DEFAULT_WRITE_TIMEOUT = 10000L


        fun create() = ApiClientConfig()

        fun getDefaultConfig(): ApiClientConfig {
            return create().apply {
                callTimeout = 20000
                connectTimeout = 20000
                readTimeout = 20000
                writeTimeout = 20000
            }
        }
    }

    private var callTimeout = DEFAULT_CALL_TIMEOUT
    private var connectTimeout = DEFAULT_CONNECT_TIMEOUT
    private var readTimeout = DEFAULT_READ_TIMEOUT
    private var writeTimeout = DEFAULT_WRITE_TIMEOUT

    private var baseUrl: String? = null
    internal var appInterceptors = mutableListOf<Interceptor>()
    internal var networkInterceptors = mutableListOf<Interceptor>()

    init {
        // 添加一些公用的拦截器
        // logging interceptor
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        appInterceptors.add(httpLoggingInterceptor)

        // common interceptor
        val commonInterceptor = CommonInterceptor()
        appInterceptors.add(commonInterceptor)
    }

    fun setBaseUrl(url: String?): ApiClientConfig {
        if (url.isNullOrEmpty())
            throw IllegalArgumentException("Base url can not be null")
        baseUrl = url
        return this
    }

    fun addApplicationInterceptor(interceptor: Interceptor?): ApiClientConfig {
        interceptor?.let {
            appInterceptors.add(it)
        }
        return this
    }

    fun addNetworkInterceptor(interceptor: Interceptor?): ApiClientConfig {
        interceptor?.let {
            networkInterceptors.add(it)
        }
        return this
    }

    fun setCallTimeout(timeout: Long): ApiClientConfig {
        callTimeout = timeout
        return this
    }

    fun setConnectTimeout(timeout: Long): ApiClientConfig {
        connectTimeout = timeout
        return this
    }

    fun setReadTimeout(timeout: Long): ApiClientConfig {
        readTimeout = timeout
        return this
    }

    fun setWriteTimeout(timeout: Long): ApiClientConfig {
        writeTimeout = timeout
        return this
    }

    fun getCallTimeout() = callTimeout
    fun getConnectTimeout() = connectTimeout
    fun getReadTimeout() = readTimeout
    fun getWriteTimeout() = readTimeout
    fun getBaseUrl() = baseUrl

    // 重写hashCode和equals，用以在ApiClient中判断不同的Retrofit
    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
