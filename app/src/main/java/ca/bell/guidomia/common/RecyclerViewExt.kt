package ca.bell.guidomia.common

import androidx.annotation.ColorRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.bell.guidomia.R


fun RecyclerView.addDivider(
    orientation: Int = LinearLayoutManager.VERTICAL,
    @ColorRes dividerColor: Int = R.color.orange,
    showLast: Boolean = true
) {
    val drawable = context.drawable(R.drawable.bg_list_divider)
    drawable?.setTint(context.color(dividerColor))
    if (drawable == null) return
    if (showLast) {
        val dividerItemDecoration = DividerItemDecoration(context, orientation)
        dividerItemDecoration.setDrawable(drawable)
        addItemDecoration(dividerItemDecoration)
    } else {
        val dividerItemDecoration = NoLastDividerItemDecorator(
            divider = drawable,
            context = context,
            orientation = orientation
        )
        dividerItemDecoration.setDrawable(drawable)
        addItemDecoration(dividerItemDecoration)
    }
}

fun RecyclerView.ViewHolder.dp2px(dp: Int): Int {
    return itemView.context.dp2px(dp)
}