package ca.bell.guidomia.data

import androidx.annotation.DrawableRes
import ca.bell.guidomia.R
import java.math.BigDecimal

data class Car(
    val consList: List<String>,
    val customerPrice: BigDecimal,
    val make: String,
    val marketPrice: BigDecimal,
    val model: String,
    val prosList: List<String>,
    val rating: Int,
)


val Car.image: Int
    @DrawableRes
    get() = when (model) {
        "Range Rover" -> R.drawable.range_rover
        "Roadster" -> R.drawable.alpine_roadster
        "3300i" -> R.drawable.bmw_330i
        "GLE coupe" -> R.drawable.mercedez_benz_glc
        else -> R.drawable.range_rover
    }