package log

import android.util.Log
import android.util.Log.*

class DefaultLog : ILog {
    private val logLengthLimit = 1024
    private val messageDivideStart =
        "\n--------------------------日志消息 start-------------------------------------\n"
    private val messageDivideEnd =
        "\n--------------------------日志消息 end---------------------------------------"

    private val throwableDivideStart =
        "\n--------------------------异常信息 start-------------------------------------\n"
    private val throwableDivideEnd =
        "--------------------------异常信息 end---------------------------------------"

    companion object {
        const val MESSAGE_START = 1
        const val MESSAGE_PROCESS = 2
        const val MESSAGE_END = 3
        const val MESSAGE_COMPLETE = 4
    }

    override fun v(tag: String?, message: String?) {
        v(tag, message, null)
    }

    override fun v(tag: String?, message: String?, throwable: Throwable?) {
        log(tag, message, throwable, VERBOSE)
    }

    override fun i(tag: String?, message: String?) {
        i(tag, message, null)
    }

    override fun i(tag: String?, message: String?, throwable: Throwable?) {
        log(tag, message, throwable, INFO)
    }

    override fun d(tag: String?, message: String?) {
        d(tag, message, null)
    }

    override fun d(tag: String?, message: String?, throwable: Throwable?) {
        log(tag, message, throwable, DEBUG)
    }

    override fun w(tag: String?, message: String?) {
        w(tag, message, null)
    }

    override fun w(tag: String?, message: String?, throwable: Throwable?) {
        log(tag, message, throwable, WARN)
    }

    override fun e(tag: String?, message: String?) {
        e(tag, message, null)
    }

    override fun e(tag: String?, message: String?, throwable: Throwable?) {
        log(tag, message, throwable, ERROR)
    }

    private fun log(tag: String?, message: String?, throwable: Throwable?, type: Int) {
        if (message.isNullOrEmpty()) return
        var rawMessage: String = message
        if (rawMessage.length > logLengthLimit) {
            val subMessage = rawMessage.substring(0, logLengthLimit)
            printLog(tag, subMessage, null, type, MESSAGE_START)
            while (rawMessage.length > logLengthLimit) {
                val subMessage = rawMessage.substring(0, logLengthLimit)
                printLog(tag, subMessage, null, type, MESSAGE_PROCESS)
                val remainingStr = rawMessage.removePrefix(subMessage)
                if (remainingStr.length < logLengthLimit) {
                    printLog(tag, remainingStr, throwable, type, MESSAGE_END)
                    break
                }
            }
        } else {
            printLog(tag, message, throwable, type, MESSAGE_COMPLETE)
        }
    }

    private fun printLog(
        tag: String?,
        message: String,
        throwable: Throwable?,
        type: Int,
        messageLocation: Int
    ) {
        when (type) {
            VERBOSE -> {
                Log.v(tag, getCompleteMessage(message, throwable, messageLocation))
            }
            DEBUG -> {
                Log.d(tag, getCompleteMessage(message, throwable, messageLocation))
            }
            INFO -> {
                Log.i(tag, getCompleteMessage(message, throwable, messageLocation))
            }
            WARN -> {
                Log.w(tag, getCompleteMessage(message, throwable, messageLocation))
            }
            ERROR -> {
                Log.e(tag, getCompleteMessage(message, throwable, messageLocation))
            }
        }
    }

    private fun getCompleteMessage(
        message: String,
        throwable: Throwable?,
        messageLocation: Int
    ): String {
        var processedMsg: String? = null
        when (messageLocation) {
            MESSAGE_START -> {
                processedMsg = messageDivideStart + message
            }
            MESSAGE_PROCESS -> {
                processedMsg = message
            }
            MESSAGE_END -> {
                processedMsg = message + messageDivideEnd
                if (throwable != null) {
                    processedMsg += throwableDivideStart + getStackTraceString(throwable) + throwableDivideEnd
                }
            }
            MESSAGE_COMPLETE -> {
                processedMsg = messageDivideStart + message + messageDivideEnd
                if (throwable != null) {
                    processedMsg += throwableDivideStart + getStackTraceString(throwable) + throwableDivideEnd
                }
            }
        }
        return processedMsg!!
    }
}