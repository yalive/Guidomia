package ca.bell.guidomia.common

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

inline fun Spinner.doOnItemSelected(
    crossinline onItemSelected: (Int) -> Unit
) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
            onItemSelected(position)
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            // Nothing
        }
    }
}