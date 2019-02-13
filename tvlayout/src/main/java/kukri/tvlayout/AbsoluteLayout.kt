package kukri.tvlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * [android.widget.AbsoluteLayout]控件不推荐使用，可能会在后续的版本中淘汰，所以这里我们定义自己的
 * [AbsoluteLayout]用于替代原生实现，其本质为[FrameLayout]
 * @author ajayniu
 * @since 2019/1/8
 */
open class AbsoluteLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        resize(child, params)
        super.addView(child, index, params)
    }
}