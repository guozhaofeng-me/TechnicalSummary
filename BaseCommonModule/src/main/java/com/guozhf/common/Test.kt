package com.guozhf.common

/**
 * User: Zephyr
 * Date: 2022/5/12
 * Time: 10:27
 */
class Test {
    fun onCreate() {
        instance = this
    }

    companion object {
        var instance: Test? = null
        fun test(): Test? {
            return instance
        }
    }
}