package ca.bell.guidomia.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.bell.guidomia.R
import ca.bell.guidomia.common.inflater
import ca.bell.guidomia.databinding.ItemCarBinding

class CarsAdapter : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    var cars: List<CarUiModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsAdapter.ViewHolder {
        val binding = ItemCarBinding.inflate(parent.inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = cars[position]
        holder.bind(car)
    }

    override fun getItemCount() = cars.size

    inner class ViewHolder(
        private val binding: ItemCarBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(car: CarUiModel) = with(binding) {
            imgCar.setImageResource(car.image)
            txtCarName.text = car.name
            txtCarPrice.text = itemView.context.getString(R.string.car_price, car.price)
            ratingBar.rating = car.rating.toFloat()
        }
    }
}