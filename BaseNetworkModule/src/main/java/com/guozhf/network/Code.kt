package com.guozhf.network

/**
 * User: Zephyr
 * Date: 2022/5/10
 * Time: 9:36
 */
const val UNKNOWN_ERROR = -1 // 未知错误
const val REQUEST_TIMEOUT = -2 // 网络请求超时
const val JSON_PARSE_ERROR = -3 // json解析错误

class HttpCode {
    companion object {
        const val BAD_REQUEST = 400 // API测试错误，无返回内容
        const val UNAUTHORIZED = 401 // 用户名密码认证失败，无返回内容
        const val AUTHORIZEFAIL = 402
        const val FORBIDDEN = 403 // 认证成功但是无权限，无返回内容
        const val NOT_FOUND = 404 // 请求的URL错误，无返回内容
        const val METHOD_NOT_ALLOW = 405 // 找不到方法
        const val NO_VISIT_PRIVILEDGE = 406 // 无权访问
        const val PRECONDITION_FAILED = 412 // 先决条件失败，无返回内容，通常是用于客户端所带的x-hs-date时间与服务器时间相差过大
        const val INTERNAL_SERVER_ERROR = 500 // 服务器内部错误
        const val GATEWAY_SERVER_ERROR = 502 // 网关错误
        const val SERVER_UNAVAILABLE = 503 // 服务不可用，无返回内容。这种情况一般是由于接口调用超出频率限制
    }
}

class RestfulCode {
    companion object {
        // 这里主要是业务错误的Code
        const val PASSWORD_ERROR = "101" // 密码错误
        const val USER_NAME_ERROR = "102" // 账号错误
        const val INVALID_TOKEN = "103" // 无效的token
        // 等等其他通用的业务错误码
    }
}