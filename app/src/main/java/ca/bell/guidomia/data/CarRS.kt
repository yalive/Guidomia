package ca.bell.guidomia.data

import androidx.annotation.DrawableRes
import ca.bell.guidomia.R
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class CarRS(
    @SerializedName("consList")
    val consList: List<String>,
    @SerializedName("customerPrice")
    val customerPrice: BigDecimal,
    @SerializedName("make")
    val make: String,
    @SerializedName("marketPrice")
    val marketPrice: BigDecimal,
    @SerializedName("model")
    val model: String,
    @SerializedName("prosList")
    val prosList: List<String>,
    @SerializedName("rating")
    val rating: Int,
)

val CarRS.image: Int
    @DrawableRes
    get() = when (model) {
        "Range Rover" -> R.drawable.range_rover
        "Roadster" -> R.drawable.alpine_roadster
        "3300i" -> R.drawable.bmw_330i
        "GLE coupe" -> R.drawable.mercedez_benz_glc
        else -> R.drawable.range_rover
    }