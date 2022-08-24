package ca.bell.guidomia.data.local

import ca.bell.guidomia.data.Car
import java.math.BigDecimal

data class CarEntity(
    val consList: List<String>,
    val customerPrice: BigDecimal,
    val make: String,
    val marketPrice: BigDecimal,
    val model: String,
    val prosList: List<String>,
    val rating: Int,
)

fun CarEntity.toCar() = Car(
    consList = consList,
    customerPrice = customerPrice,
    make = make,
    marketPrice = marketPrice,
    model = model,
    prosList = prosList,
    rating = rating,
)