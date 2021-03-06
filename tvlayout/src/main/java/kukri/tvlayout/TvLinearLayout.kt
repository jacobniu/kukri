package kukri.tvlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

/**
 * @author ajayniu
 * @since 2019/1/8
 */
open class TvLinearLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        resize(child, params)
        super.addView(child, index, params)
    }
}