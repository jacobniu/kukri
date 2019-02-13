package kukri.util.extension

import android.content.Context
import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

/**
 * @author ajayniu
 * @since 2019/1/8
 */

/**
 * 在应用程序的ui线程执行[f]
 */
fun Context.runOnUiThread(f: Context.() -> Unit) {
    if (Looper.getMainLooper() === Looper.myLooper()) f() else ContextHelper.handler.post { f() }
}

private object ContextHelper {
    val handler = Handler(Looper.getMainLooper())
}

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * 在后台线程中执行[f]，一般用于处理io或者数据库任务。
 */
fun runOnIoThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}