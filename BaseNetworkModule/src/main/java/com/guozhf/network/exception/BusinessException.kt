package com.guozhf.network.exception

/**
 * User: Zephyr
 * Date: 2022/5/10
 * Time: 9:49
 */
class BusinessException : Exception {
    var code: Int? = -1
    var msg: String? = null

    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(message: String?, code: Int) {
        this.code = code
        msg = message
    }
    constructor(cause: Throwable?) : super(cause)
    constructor(
        message: String?,
        cause: Throwable?,
        enableSuppression: Boolean,
        writableStackTrace: Boolean
    ) : super(message, cause, enableSuppression, writableStackTrace)
}