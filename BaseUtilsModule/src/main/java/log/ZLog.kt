package log

object ZLog : ILog {
    private var isDebuggable: Boolean = true
    private var isSaveToLocalFile: Boolean = false
    private var logFilePath: String? = null
    private var logStrategy: ILog = DefaultLog()
    private var logTag: String = "Log" // 全局的日志Tag，默认为Log

    fun setLogTag(tag: String): ZLog {
        logTag = tag
        return this
    }

    fun setDebuggable(isDebug: Boolean): ZLog {
        this.isDebuggable = isDebug
        return this
    }

    fun setSaveToFile(isSaveToLocalFile: Boolean): ZLog {
        this.isSaveToLocalFile = isSaveToLocalFile
        return this
    }

    fun setLogFilePath(path: String?): ZLog {
        logFilePath = path
        return this
    }

    fun setLogStrategy(strategy: ILog) {
        logStrategy = strategy
    }

    fun isDebuggable() = isDebuggable

    fun isSaveToFile() = isSaveToLocalFile

    override fun v(tag: String?, message: String?) {
        if (!isDebuggable) return
        if (tag.isNullOrEmpty()) {
            logStrategy.v(logTag, message)
        } else {
            logStrategy.v(tag, message)
        }
    }

    override fun v(tag: String?, message: String?, throwable: Throwable?) {
        if (!isDebuggable) return
        if (tag.isNullOrEmpty()) {
            logStrategy.v(logTag, message, throwable)
        } else {
            logStrategy.v(tag, message, throwable)
        }
    }

    override fun i(tag: String?, message: String?) {
        if (!isDebuggable) return
        if (tag.isNullOrEmpty()) {
            logStrategy.i(logTag, message)
        } else {
            logStrategy.i(tag, message)
        }
    }

    override fun i(tag: String?, message: String?, throwable: Throwable?) {
        if (!isDebuggable) return
        if (tag.isNullOrEmpty()) {
            logStrategy.i(logTag, message, throwable)
        } else {
            logStrategy.i(tag, message, throwable)
        }
    }

    override fun d(tag: String?, message: String?) {
        if (!isDebuggable) return
        if (tag.isNullOrEmpty()) {
            logStrategy.d(logTag, message)
        } else {
            logStrategy.d(tag, message)
        }
    }

    override fun d(tag: String?, message: String?, throwable: Throwable?) {
        if (!isDebuggable) return
        if (tag.isNullOrEmpty()) {
            logStrategy.d(logTag, message, throwable)
        } else {
            logStrategy.d(tag, message, throwable)
        }
    }

    override fun w(tag: String?, message: String?) {
        if (!isDebuggable) return
        if (tag.isNullOrEmpty()) {
            logStrategy.w(logTag, message)
        } else {
            logStrategy.w(tag, message)
        }
    }

    override fun w(tag: String?, message: String?, throwable: Throwable?) {
        if (!isDebuggable) return
        if (tag.isNullOrEmpty()) {
            logStrategy.w(logTag, message, throwable)
        } else {
            logStrategy.w(tag, message, throwable)
        }
    }

    override fun e(tag: String?, message: String?) {
        if (!isDebuggable) return
        if (tag.isNullOrEmpty()) {
            logStrategy.e(logTag, message)
        } else {
            logStrategy.e(tag, message)
        }
    }

    override fun e(tag: String?, message: String?, throwable: Throwable?) {
        if (!isDebuggable) return
        if (tag.isNullOrEmpty()) {
            logStrategy.e(logTag, message, throwable)
        } else {
            logStrategy.e(tag, message, throwable)
        }
    }

}