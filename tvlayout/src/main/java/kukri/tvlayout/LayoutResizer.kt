package kukri.tvlayout

import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * @author ajayniu
 * @since 2019/1/8
 */

/**
 * 重新调整控件大小参数
 */
fun resize(child: View, params: ViewGroup.LayoutParams) {
    calculate(child, params, RADIO_WIDTH, RADIO_HEIGHT)
}

/**
 * 根据指定的 radioW 和 radioH 重新调整 View 的布局参数
 * @param child  需要被调整布局参数的布局
 * @param params 原始的布局参数
 * @param radioW 宽度比率
 * @param radioH 高度比率
 */
fun resize(child: View, params: ViewGroup.LayoutParams, radioW: Float, radioH: Float) {
    calculate(child, params, radioW, radioH)
}

private fun calculate(child: View, p: ViewGroup.LayoutParams, radioW: Float, radioH: Float) {
    /** width and height **/
    p.width = if (p.width > 0) ceil(p.width, radioW) else p.width
    p.height = if (p.height > 0) ceil(p.height, radioH) else p.height

    /** margin **/
    if (p is ViewGroup.MarginLayoutParams) {
        /** left, top, right, bottom **/
        val left = p.leftMargin
        val top = p.topMargin
        val right = p.rightMargin
        val bottom = p.bottomMargin
        p.leftMargin = if (left == UNDEFINED_MARGIN) left else ceil(left, radioW)
        p.topMargin = if (top == UNDEFINED_MARGIN) top else ceil(top, radioH)
        p.rightMargin = if (right == UNDEFINED_MARGIN) right else ceil(right, radioW)
        p.bottomMargin = if (bottom == UNDEFINED_MARGIN) bottom else ceil(bottom, radioH)

        /** start, end **/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val direction = child.layoutDirection
            val lm = p.leftMargin
            val rm = p.rightMargin
            val start = p.marginStart
            val end = p.marginEnd
            when (direction) {
                View.LAYOUT_DIRECTION_LTR -> {
                    if (lm == UNDEFINED_MARGIN) {
                        p.leftMargin = if (start == UNDEFINED_MARGIN) lm else ceil(start, radioW)
                    }
                    if (rm == UNDEFINED_MARGIN) {
                        p.rightMargin = if (end == UNDEFINED_MARGIN) rm else ceil(end, radioW)
                    }
                }
                View.LAYOUT_DIRECTION_RTL -> {
                    if (lm == UNDEFINED_MARGIN) {
                        p.leftMargin = if (end == UNDEFINED_MARGIN) lm else ceil(end, radioW)
                    }
                    if (rm == UNDEFINED_MARGIN) {
                        p.rightMargin = if (start == UNDEFINED_MARGIN) rm else ceil(start, radioW)
                    }
                }
            }

            /** padding **/
            val paddingLeft = ceil(child.paddingLeft, radioW)
            val paddingTop = ceil(child.paddingTop, radioH)
            val paddingRight = ceil(child.paddingRight, radioW)
            val paddingBottom = ceil(child.paddingBottom, radioH)
            child.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)

            /** font size and others **/
            if (child is TextView) {
                child.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    ceil(child.textSize.toInt(), (radioW + radioH) / 2.0f).toFloat()
                )
                child.compoundDrawablePadding = ceil(child.compoundDrawablePadding, (radioW + radioH) / 2.0f)
            }
        }
    }
}

/**
 * 对给定的值取整
 */
private fun ceil(value: Int, radio: Float) = Math.ceil(value * radio.toDouble()).toInt()

/**
 * 默认的margin值
 */
private const val UNDEFINED_MARGIN = Int.MIN_VALUE