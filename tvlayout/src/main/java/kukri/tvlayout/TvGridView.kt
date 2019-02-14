package kukri.tvlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.GridView

/**
 * @author ajayniu
 * @since 2019/1/8
 */
open class TvGridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : GridView(context, attrs, defStyleAttr) {
    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        resize(child, params)
        super.addView(child, index, params)
    }
}