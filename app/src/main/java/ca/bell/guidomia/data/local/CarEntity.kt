package ca.bell.guidomia.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ca.bell.guidomia.data.Car
import java.math.BigDecimal

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    val uid: Int,
    @ColumnInfo(name = "cons_list")
    val consList: List<String>,
    @ColumnInfo(name = "customer_price")
    val customerPrice: BigDecimal,
    @ColumnInfo(name = "make")
    val make: String,
    @ColumnInfo(name = "market_price")
    val marketPrice: BigDecimal,
    @ColumnInfo(name = "model")
    val model: String,
    @ColumnInfo(name = "pros_list")
    val prosList: List<String>,
    @ColumnInfo(name = "rating")
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