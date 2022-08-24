package ca.bell.guidomia.data.local.db

import androidx.room.TypeConverter
import java.math.BigDecimal

class BigDecimalConverter {
    @TypeConverter
    fun fromDouble(value: Double): BigDecimal {
        return value.toBigDecimal()
    }

    @TypeConverter
    fun bigDecimalToDouble(bd: BigDecimal): Double {
        return bd.toDouble()
    }
}

class StringToListConverter {

    private val separator = "#_##_#"

    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(separator)
    }

    @TypeConverter
    fun stringListToString(items: List<String>): String {
        return items.joinToString(separator)
    }
}