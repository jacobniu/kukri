package kukri.tvlayout.extension

import kukri.tvlayout.RADIO_AVERAGE

/**
 * @author ajayniu
 * @since 2019/1/8
 */

/**
 * 获取分辨率适配之后的真实大小
 */
fun Int.fitSize() = this * RADIO_AVERAGE.toInt()

/**
 * 获取分辨率适配之后的真实大小
 */
fun Float.fitSize() = this * RADIO_AVERAGE

/**
 * 获取分辨率适配之后的真实大小
 */
fun Double.fitSize() = this * RADIO_AVERAGE.toDouble()