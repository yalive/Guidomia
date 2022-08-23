package ca.bell.guidomia.ui

import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import ca.bell.guidomia.R
import ca.bell.guidomia.common.color
import ca.bell.guidomia.common.dp2px
import ca.bell.guidomia.common.inflater
import ca.bell.guidomia.databinding.ItemCarBinding


class CarsAdapter(
    private val onClickCar: (CarUiModel) -> Unit
) : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    private val differ = AsyncListDiffer(this, CarDiffUtil())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsAdapter.ViewHolder {
        val binding = ItemCarBinding.inflate(parent.inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = differ.currentList[position]
        holder.bind(car)
    }

    override fun getItemCount() = differ.currentList.size

    fun submitList(newList: List<CarUiModel>, callback: () -> Unit = {}) {
        differ.submitList(newList, callback)
    }

    inner class ViewHolder(
        private val binding: ItemCarBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(carUiModel: CarUiModel) = with(binding) {
            imgCar.setImageResource(carUiModel.image)
            txtCarName.text = carUiModel.name
            txtCarPrice.text =
                itemView.context.getString(R.string.car_price, carUiModel.price)
            ratingBar.rating = carUiModel.rating.toFloat()

            // Draw cons and Pros items
            prosContainer.removeAllViews()
            consContainer.removeAllViews()

            val prosList = carUiModel.prosList
            val consList = carUiModel.consList

            prosList.forEach { pro ->
                prosContainer.addView(bulletTextView(pro))
            }

            consList.forEach { con ->
                consContainer.addView(bulletTextView(con))
            }

            prosGroup.isVisible = prosList.isNotEmpty()
            consGroup.isVisible = consList.isNotEmpty()

            // Expanded state
            detailView.isVisible = carUiModel.expanded

            itemView.setOnClickListener {
                onClickCar(carUiModel)
            }
        }

        private fun bulletTextView(text: String): TextView {
            val textView = TextView(itemView.context)
            textView.text = text
            textView.setTextColor(itemView.context.color(R.color.black))
            textView.setTypeface(textView.typeface, Typeface.BOLD)
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.bullet,
                0,
                0,
                0
            )
            textView.compoundDrawablePadding = dp2px(10)
            return textView
        }
    }
}