package log

interface ILog {
    fun v(tag: String? = null, message: String?)
    fun v(tag: String? = null, message: String?, throwable: Throwable?)
    fun i(tag: String? = null, message: String?)
    fun i(tag: String? = null, message: String?, throwable: Throwable?)
    fun d(tag: String? = null, message: String?)
    fun d(tag: String? = null, message: String?, throwable: Throwable?)
    fun w(tag: String? = null, message: String?)
    fun w(tag: String? = null, message: String?, throwable: Throwable?)
    fun e(tag: String? = null, message: String?)
    fun e(tag: String? = null, message: String?, throwable: Throwable?)
}