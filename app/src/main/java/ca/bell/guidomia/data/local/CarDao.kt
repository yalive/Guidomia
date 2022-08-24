package ca.bell.guidomia.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarDao {

    @Query("SELECT * FROM cars")
    fun getAll(): List<CarEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<CarEntity>)
}