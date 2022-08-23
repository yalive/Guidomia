package ca.bell.guidomia.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.bell.guidomia.common.inflater
import ca.bell.guidomia.databinding.ItemCarBinding

class CarsAdapter(
    private val cars: List<CarUiModel>
) : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsAdapter.ViewHolder {
        val binding = ItemCarBinding.inflate(parent.inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = cars[position]
        holder.bind(car)
    }

    override fun getItemCount() = cars.size

    inner class ViewHolder(val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(car: CarUiModel) {
            binding.imgCar.setImageResource(car.image)
            binding.txtCarName.text = car.name
            binding.txtCarPrice.text = car.price
            binding.ratingBar.rating = car.rating.toFloat()
        }
    }
}