package ca.bell.guidomia.common

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.Fragment

// Convert dp value to pixels
fun Context.dp2px(dp: Float): Int {
    return (dp * (this.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}

fun Context.dp2px(dp: Int): Int {
    return dp2px(dp.toFloat())
}

// Access in fragment
fun Fragment.dp2px(dp: Float): Int {
    return requireContext().dp2px(dp)
}

fun Fragment.dp2px(dp: Int): Int {
    return dp2px(dp.toFloat())
}

// Access in view
fun View.dp2px(dp: Float): Int {
    return context.dp2px(dp)
}

fun View.dp2px(dp: Int): Int {
    return dp2px(dp.toFloat())
}