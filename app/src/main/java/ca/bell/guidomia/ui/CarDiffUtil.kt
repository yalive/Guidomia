package ca.bell.guidomia.ui

import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DiffUtil

class CarDiffUtil : DiffUtil.ItemCallback<CarUiModel>() {

    override fun areItemsTheSame(oldItem: CarUiModel, newItem: CarUiModel): Boolean {
        return oldItem.identifier == newItem.identifier
    }

    override fun areContentsTheSame(oldItem: CarUiModel, newItem: CarUiModel): Boolean {
        return newItem == oldItem
    }

    override fun getChangePayload(oldItem: CarUiModel, newItem: CarUiModel): Any? {
        if (oldItem.identifier == newItem.identifier && oldItem.expanded != newItem.expanded) {
            // Only expanded state is changed
            return bundleOf("expanded" to newItem.expanded)
        }
        return super.getChangePayload(oldItem, newItem)
    }
}