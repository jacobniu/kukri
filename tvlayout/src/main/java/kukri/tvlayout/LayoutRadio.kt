package kukri.tvlayout

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.Log
import android.view.WindowManager

/**
 * @author ajayniu
 * @since 2019/1/8
 */

/** 默认分辨率为1080p **/
var STANDARD_WIDTH = 1920
var STANDARD_HEIGHT = 1080

var REAL_WIDTH = 0
var REAL_HEIGHT = 0

var RADIO_WIDTH = 1.0f
var RADIO_HEIGHT = 1.0f
var RADIO_AVERAGE = 1.0f

/**
 * 初始化比率，默认的标准宽和高采用 1920 * 1080
 * @param context 上下文
 */
fun initRadio(context: Context) {
    initRadio(context, STANDARD_WIDTH, STANDARD_HEIGHT)
}

/**
 * 初始化比率，根据传入的值来设置标准的宽和高，传入的宽和高
 * 必须大于 0，否则会抛出 IllegalArgumentException 异常
 * @param context        上下文
 * @param standardWidth  用户定义的标准宽
 * @param standardHeight 用于定义的标准高
 */
fun initRadio(context: Context, standardWidth: Int, standardHeight: Int) {
    if (standardWidth > 0 && standardHeight > 0) {
        STANDARD_WIDTH = standardWidth
        STANDARD_HEIGHT = standardHeight
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val point = Point()
            display.getRealSize(point)
            REAL_WIDTH = point.x
            REAL_HEIGHT = point.y
        } else {
            val metrics = Resources.getSystem().displayMetrics
            Resources.getSystem().configuration
            REAL_WIDTH = metrics.widthPixels
            REAL_HEIGHT = metrics.heightPixels
        }
        RADIO_WIDTH = REAL_WIDTH / STANDARD_WIDTH.toFloat()
        RADIO_HEIGHT = REAL_HEIGHT / STANDARD_HEIGHT.toFloat()
        RADIO_AVERAGE = (RADIO_WIDTH + RADIO_HEIGHT) / 2
        Log.d(
            TAG, "REAL_WIDTH = " + REAL_WIDTH
                    + ", REAL_HEIGHT = " + REAL_HEIGHT
                    + ", STANDARD_WIDTH = " + STANDARD_WIDTH
                    + ", STANDARD_HEIGHT = " + STANDARD_HEIGHT
                    + ", RADIO_WIDTH = " + RADIO_WIDTH
                    + ", RADIO_HEIGHT = " + RADIO_HEIGHT
                    + ", RADIO_AVERAGE = " + RADIO_AVERAGE
        )
    } else {
        throw IllegalArgumentException("Both standard width and height should be positive.")
    }
}

private const val TAG = "#LayoutRadio"