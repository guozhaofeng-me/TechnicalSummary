package com.guozhf.network

import com.guozhf.common.BaseApplication
import com.guozhf.network.interceptor.CacheInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * User: Zephyr
 * Date: 2022/5/6
 * Time: 11:31
 */
//支持Retrofit的单例模式配置，一次配置多处使用。
//支持动态切换baseUrl不影响原有的baseUrl。
//支持通用格式的网络请求，返回对象或者字符串。
//支持快捷的请求方式返回字符串格式的数据。
//支持大文件下载和进度回调以及动态取消。
//支持单文件和多文件上传。
object ApiClient {
    private val defaultConfig = ApiClientConfig.getDefaultConfig()// 默认的网络配置

    private val retrofitMap = hashMapOf<Int, Retrofit>()

    private const val cacheFileName = "http_cache"

    fun setDefaultBaseUrl(url: String?) {
        if (url.isNullOrEmpty())
            throw IllegalArgumentException("Base url can not be null")
        defaultConfig.setBaseUrl(url)
    }

    /**
     * 通过默认的baseurl获取Retrofit实例
     * 大多数应用中不会有多个baseurl，那么使用这个方法即可
     * 如果有多个baseurl，则使用getRetrofit方法
     * @return Retrofit
     */
    fun getDefaultRetrofit(): Retrofit = getRetrofit()

    /**
     * 根据BaseUrl来获取Retrofit实例
     * @return Retrofit
     */
    fun getRetrofit(config: ApiClientConfig = defaultConfig): Retrofit {
        val baseUrl = config.getBaseUrl()
        if (baseUrl.isNullOrEmpty())
            throw IllegalAccessException("You should call method[setDefaultBaseUrl] first")
//        return retrofitMap[baseUrl] ?: createRetrofit(config)
        return retrofitMap[config.hashCode()] ?: createRetrofit(config)
    }

    /**
     * 根据baseurl创建Retrofit实例
     * @return Retrofit
     */
    private fun createRetrofit(config: ApiClientConfig): Retrofit {
        val baseUrl = config.getBaseUrl()
        val builder = Retrofit.Builder()
        val retrofit = builder.baseUrl(baseUrl!!)
            .client(createOkHttpClient(config))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
//        retrofitMap[baseUrl] = retrofit
        retrofitMap[config.hashCode()] = retrofit
        return retrofit
    }

    /**
     * 初始化OkHttpClient
     * 拦截器
     * 日志
     * 缓存
     * 超时
     * @return OkHttpClient
     */
    private fun createOkHttpClient(config: ApiClientConfig): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.callTimeout(config.getCallTimeout(), TimeUnit.MILLISECONDS)
            .connectTimeout(config.getConnectTimeout(), TimeUnit.MILLISECONDS)
            .readTimeout(config.getReadTimeout(), TimeUnit.MILLISECONDS)
            .writeTimeout(config.getWriteTimeout(), TimeUnit.MILLISECONDS)

        builder.retryOnConnectionFailure(true) // 设置失败重连

        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        builder.addInterceptor(httpLoggingInterceptor)
        if (BaseApplication.isDebug) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }

        // 增加缓存
        builder.cache(createCache())
        // 添加缓存拦截器
        builder.addInterceptor(CacheInterceptor())

        for (interceptor in config.appInterceptors) { // 添加应用拦截器
            builder.addInterceptor(interceptor)
        }
        for (interceptor in config.networkInterceptors) { // 添加网络拦截器
            builder.addNetworkInterceptor(interceptor)
        }
        // TODO https相关设置
        return builder.build()
    }

    /**
     * 创建缓存，需要和CacheInterceptor配合使用
     * @return Cache
     */
    private fun createCache(): Cache {
        val file = File(BaseApplication.getContext()?.externalCacheDir, cacheFileName)
        return Cache(file, (1024 * 1024 * 50).toLong())
    }
}