package ca.bell.guidomia.ui

import androidx.annotation.DrawableRes
import ca.bell.guidomia.data.CarRS
import ca.bell.guidomia.data.image
import java.util.*

data class CarUiModel(
    val identifier: String,
    val name: String,
    val make: String,
    val price: Int,
    val rating: Int,
    @DrawableRes val image: Int,
    val prosList: List<String>,
    val consList: List<String>,
    val expanded: Boolean
) {
    companion object {
        operator fun invoke(car: CarRS, expanded: Boolean) = CarUiModel(
            identifier = UUID.randomUUID().toString(),
            name = car.model,
            make = car.make,
            price = car.marketPrice.toInt() / 1000,
            rating = car.rating,
            image = car.image,
            prosList = car.prosList,
            consList = car.consList,
            expanded = expanded
        )
    }
}