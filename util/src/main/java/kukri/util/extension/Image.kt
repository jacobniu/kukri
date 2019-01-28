package kukri.util.extension

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes

/**
 * @author ajayniu
 * @since 2019/1/9
 */

/**
 * 在[Bitmap]上绘制颜色
 */
fun Bitmap.drawColor(@ColorInt color: Int): Bitmap {
    if (isMutable) {
        val canvas = Canvas(this)
        canvas.drawColor(color)
    }
    return this
}

/**
 * 调整[Bitmap]宽高尺寸
 */
fun Bitmap.resize(scaleRatioW: Float, scaleRatioH: Float): Bitmap {
    val matrix = Matrix()
    matrix.postScale(scaleRatioW, scaleRatioH)
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}

/**
 * [Bitmap]转[android.graphics.drawable.Drawable]
 */
fun Bitmap.toDrawable(res: Resources) = BitmapDrawable(res, this)

/**
 * 根据给定的尺寸解码[Bitmap]
 */
fun Bitmap.decode(res: Resources, @DrawableRes resId: Int, w: Int = 0, h: Int = 0): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(res, resId, options)
    options.inSampleSize = calculateInSampleSize(options, w, h)
    options.inJustDecodeBounds = false
    return BitmapFactory.decodeResource(res, resId, options)
}

private fun calculateInSampleSize(options: BitmapFactory.Options, w: Int, h: Int): Int {
    var inSampleSize = 1
    if (options.outWidth > w || options.outHeight > h) {
        val widthRatio = Math.round(options.outWidth / w.toFloat())
        val heightRatio = Math.round(options.outHeight / h.toFloat())
        inSampleSize = Math.max(widthRatio, heightRatio)
    }
    return inSampleSize
}

/**
 * [android.graphics.drawable.Drawable]转[Bitmap]
 */
fun Drawable.toBitmap(): Bitmap {
    val config = if (opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565
    val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, intrinsicWidth, intrinsicHeight)
    draw(canvas)
    return bitmap
}

/**
 * 根据给定大小进行截屏操作，该功能通过反射调用，可能存在版本兼容问题
 */
@SuppressLint("PrivateApi")
fun screenshot(w: Int, h: Int): Bitmap? {
    var bitmap: Bitmap? = null
    try {
        val matrix: Class<*> = if (Build.VERSION.SDK_INT >= 18) Class.forName("android.view.SurfaceControl")
        else Class.forName("android.view.Surface")
        val method = matrix.getMethod("screenshot", Integer.TYPE, Integer.TYPE)
        bitmap = method.invoke(null, w, h) as Bitmap
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    return bitmap
}