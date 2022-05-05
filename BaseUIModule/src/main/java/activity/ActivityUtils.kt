package activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.ArrayMap

object ActivityUtils {
    fun <R> startActivity(
        activity: Activity,
        bundle: Bundle?,
        clazz: Class<R>,
        isFinish: Boolean = false,
        isAnim: Boolean = false
    ) {
        val intent = Intent(activity, clazz)
        bundle?.let {
            intent.putExtras(bundle)
        }
        activity.startActivity(intent)
        if (isFinish) activity.finish()
        if (isAnim) { // 过渡动画


        }
    }
}