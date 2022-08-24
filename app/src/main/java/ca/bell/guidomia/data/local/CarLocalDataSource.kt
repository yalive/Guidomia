package ca.bell.guidomia.data.local

import ca.bell.guidomia.data.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CarLocalDataSource(
    private val carDao: CarDao
) {

    suspend fun getCarList(): List<Car> = withContext(Dispatchers.IO) {
        return@withContext carDao.getAll().map(CarEntity::toCar)
    }

    suspend fun saveCars(cars: List<Car>) = withContext(Dispatchers.IO) {
        carDao.insertAll(cars.map {
            CarEntity(
                uid = 0,
                consList = it.consList,
                customerPrice = it.customerPrice,
                make = it.make,
                marketPrice = it.marketPrice,
                model = it.model,
                prosList = it.prosList,
                rating = it.rating,
            )
        })
    }
}