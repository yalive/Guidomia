package ca.bell.guidomia.ui

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
            return CarItemToggled(newItem.expanded)
        }
        return super.getChangePayload(oldItem, newItem)
    }
}

// Represent data diff between two versions of the same item
data class CarItemToggled(
    val expanded: Boolean
)