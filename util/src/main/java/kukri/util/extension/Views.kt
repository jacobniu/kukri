package kukri.util.extension

import android.support.v4.view.ViewCompat
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator

/**
 * @author ajayniu
 * @since 2019/1/17
 */

/**
 * Returns true if child == parent or is descendant of the parent.
 */
fun View?.isDescendants(parent: ViewGroup?): Boolean {
    var child = this
    while (child != null) {
        if (child == parent) return true
        val p = child.parent as? View ?: return false
        child = p
    }
    return false
}

/**
 * 显示[View]
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * 隐藏[View]
 */
fun View.hide(v: Int = View.INVISIBLE) {
    visibility = v
}

/**
 * 以透明度动画的形式显示[View]
 */
fun View.alphaShow(duration: Long = 400L) {
    ViewCompat.animate(this).alpha(1f).setDuration(duration).start()
}

/**
 * 以透明度动画的形式隐藏[View]
 */
fun View.alphaHide(duration: Long = 400L) {
    ViewCompat.animate(this).alpha(0f).setDuration(duration).start()
}

private object ZoomUtils {
    const val zoomInRatio = 1.1f
    const val zoomOutRatio = 1f
    val decelerateInterpolator = DecelerateInterpolator()
}

/**
 * 放大[View]，默认比例为1.1
 */
fun View.zoomIn(ratio: Float = ZoomUtils.zoomInRatio) {
    zoom(ratio, ratio)
}

/**
 * 缩小[View]，默认比例为1
 */
fun View.zoomOut(ratio: Float = ZoomUtils.zoomOutRatio) {
    zoom(ratio, ratio)
}

/**
 * 缩放[View]
 */
fun View.zoom(xTo: Float, yTo: Float, duration: Long = 400L) {
    ViewCompat.animate(this)
        .scaleX(xTo)
        .scaleY(yTo)
        .setDuration(duration)
        .setInterpolator(ZoomUtils.decelerateInterpolator)
        .start()
}