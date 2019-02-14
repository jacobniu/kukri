@file:Suppress("NOTHING_TO_INLINE", "unused")

package kukri.util.extension

import android.content.Context
import android.widget.Toast

/**
 * @author ajayniu
 * @since 2019/1/7
 */

/**
 * 在[Toast.LENGTH_SHORT]时长内显示简单的吐司信息
 * @param message 信息文本的资源ID
 */
inline fun Context.toast(message: Int): Toast = Toast
        .makeText(this, message, Toast.LENGTH_SHORT)
        .apply {
            show()
        }

/**
 * 在[Toast.LENGTH_SHORT]时长内显示简单的吐司信息
 * @param message 要显示的信息文本
 */
inline fun Context.toast(message: CharSequence): Toast = Toast
        .makeText(this, message, Toast.LENGTH_SHORT)
        .apply {
            show()
        }

/**
 * 在[Toast.LENGTH_LONG]时长内显示简单的吐司信息
 * @param message 信息文本的资源ID
 */
inline fun Context.longToast(message: Int): Toast = Toast
        .makeText(this, message, Toast.LENGTH_LONG)
        .apply {
            show()
        }

/**
 * 在[Toast.LENGTH_LONG]时长内显示简单的吐司信息
 * @param message 要显示的信息文本
 */
inline fun Context.longToast(message: CharSequence): Toast = Toast
        .makeText(this, message, Toast.LENGTH_LONG)
        .apply {
            show()
        }