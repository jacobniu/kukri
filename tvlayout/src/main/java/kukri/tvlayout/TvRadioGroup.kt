package kukri.tvlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup

/**
 * @author ajayniu
 * @since 2019/1/8
 */
open class TvRadioGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RadioGroup(context, attrs) {
    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        resize(child, params)
        super.addView(child, index, params)
    }
}