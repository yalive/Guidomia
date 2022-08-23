package ca.bell.guidomia.ui

import androidx.recyclerview.widget.DiffUtil

class CarDiffUtil : DiffUtil.ItemCallback<CarUiModel>() {

    override fun areItemsTheSame(oldItem: CarUiModel, newItem: CarUiModel): Boolean {
        return oldItem.identifier == newItem.identifier
    }

    override fun areContentsTheSame(oldItem: CarUiModel, newItem: CarUiModel): Boolean {
        return newItem == oldItem
    }
}