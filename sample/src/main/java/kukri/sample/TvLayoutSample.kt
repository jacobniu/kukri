package kukri.sample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_tvlayout.*
import kukri.tvlayout.extension.fitSize
import kukri.tvlayout.initRadio

/**
 * @author ajayniu
 * @since 2019/1/28
 */
class TvLayoutSample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初始化分辨率适配参数，一般在application中设置
        initRadio(this, 1920, 1080)
        setContentView(R.layout.activity_tvlayout)

        // 通过代码添加的组件同样可以适配
        val v1 = View(this)
        v1.setBackgroundColor(Color.RED)
        v1.layoutParams = ViewGroup.LayoutParams(480, 270)
        container.addView(v1)

        recycler_view.layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        recycler_view.adapter = Adapter()
    }

    class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = View(parent.context)
            // recycler view子控件分辨率适配暂时需要手动适配
            v.layoutParams = ViewGroup.LayoutParams(240.fitSize(), 135.fitSize())
            v.isFocusable = true
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount() = 50

        class ViewHolder constructor(v: View) : RecyclerView.ViewHolder(v) {
            fun bind(position: Int) {
                when {
                    position % 3 == 0 -> itemView.setBackgroundColor(Color.RED)
                    position % 3 == 1 -> itemView.setBackgroundColor(Color.BLUE)
                    else -> itemView.setBackgroundColor(Color.GREEN)
                }
            }
        }
    }
}