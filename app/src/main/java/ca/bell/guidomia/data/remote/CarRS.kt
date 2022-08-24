package ca.bell.guidomia.data.remote

import ca.bell.guidomia.data.Car
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

fun CarRS.toCar() = Car(
    consList = consList,
    customerPrice = customerPrice,
    make = make,
    marketPrice = marketPrice,
    model = model,
    prosList = prosList,
    rating = rating,
)