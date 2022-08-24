package ca.bell.guidomia.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import ca.bell.guidomia.common.inflater
import ca.bell.guidomia.databinding.ItemSpinnerDropdownBinding
import ca.bell.guidomia.databinding.SpinnerViewBinding

class SpinnerArrayAdapter(
    context: Context,
    private val values: List<String>
) : ArrayAdapter<String>(context, 0, values) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = SpinnerViewBinding.inflate(parent.inflater, parent, false)
        val txtDisplayedValue = binding.txtDisplayedValue
        txtDisplayedValue.text = values[position]
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerDropdownBinding.inflate(parent.inflater, parent, false)
        val txtDisplayedValue = binding.txtDisplayedValue
        txtDisplayedValue.text = values[position]
        return binding.root
    }
}