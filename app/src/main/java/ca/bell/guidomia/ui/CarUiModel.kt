package ca.bell.guidomia.ui

import androidx.annotation.DrawableRes
import ca.bell.guidomia.data.CarRS
import ca.bell.guidomia.data.image

data class CarUiModel(
    val name: String,
    val price: Int,
    val rating: Int,
    @DrawableRes val image: Int
) {
    companion object {
        operator fun invoke(car: CarRS) = CarUiModel(
            name = car.model,
            price = car.marketPrice.toInt() / 1000,
            rating = car.rating,
            image = car.image
        )
    }
}