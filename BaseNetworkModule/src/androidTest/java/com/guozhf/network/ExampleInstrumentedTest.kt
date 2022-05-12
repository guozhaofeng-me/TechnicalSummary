package com.guozhf.network

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.guozhf.network.api.Api
import com.guozhf.network.exception.BusinessException
import com.guozhf.network.nativecall.RetrofitHelper

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val TAG = "test"

    @Test
    fun testGetRequest(): List<String> {
        try {
            val data = RetrofitHelper.executeCall(ApiFactory.api.testGet("abc"))
            Log.d(TAG, "testGetRequest: ${data.size}")// 直接打印数据
            return data // 拿到数据了
        } catch (ex: BusinessException) {
            when (ex.code) {
                // 单独处理的一般都是业务错误，比如token失效，可能需要直接跳转到登录页面，用户不存在可能直接跳转到注册页面等。
                101 -> { // 假设是token失效

                }

                102 -> { // 假设是用户不存在

                }

                103 -> { // 假设是密码错误

                }

                104 -> { // 假设是其他需要单独处理的业务

                }

                else -> { // 未知错误，网络错误，数据解释错误，网络超时等可以直接弹窗显示
                    // 弹窗显示错误内容,这里用日志表示
                    Log.d(TAG, "testGetRequest: ")
                }
            }
        }
        return listOf()
    }
}