package ca.bell.guidomia.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.bell.guidomia.data.local.CarDao
import ca.bell.guidomia.data.local.CarEntity

@Database(entities = [CarEntity::class], version = 1)
@TypeConverters(BigDecimalConverter::class, StringToListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao

    companion object {

        operator fun invoke(appContext: Context): AppDatabase {
            return Room.databaseBuilder(
                appContext,
                AppDatabase::class.java, "cars_db"
            ).build()
        }
    }
}