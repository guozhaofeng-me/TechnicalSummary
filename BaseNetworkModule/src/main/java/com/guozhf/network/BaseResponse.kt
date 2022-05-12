package com.guozhf.network

/**
 * User: Zephyr
 * Date: 2022/5/10
 * Time: 10:33
 * 和后端定义的返回数据的格式
 */
class BaseResponse<T> {
    var code: String? = null
    var message: String? = null
    var data: T? = null
}