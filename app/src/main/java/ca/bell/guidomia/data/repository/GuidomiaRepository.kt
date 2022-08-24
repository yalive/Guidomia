package ca.bell.guidomia.data.repository

import ca.bell.guidomia.data.Car
import ca.bell.guidomia.data.local.CarLocalDataSource
import ca.bell.guidomia.data.remote.CarRemoteDataSource

class GuidomiaRepository(
    private val localDataSource: CarLocalDataSource,
    private val remoteDataSource: CarRemoteDataSource
) {

    suspend fun getCars(): List<Car> {
        val localCars = localDataSource.getCarList()
        if (localCars.isNotEmpty()) return localCars

        // Fetch from remote then save to DB
        val remoteCars = remoteDataSource.getCarList()
        localDataSource.saveCars(remoteCars)

        // DB as single source of truth
        return localDataSource.getCarList()
    }
}
