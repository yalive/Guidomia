package ca.bell.guidomia.data.local

import ca.bell.guidomia.data.Car

class CarLocalDataSource {

    var carList: List<Car> = emptyList()

    suspend fun getCarList(): List<Car> {
        return carList
    }

    suspend fun saveCars(cars: List<Car>) {
        carList = cars
    }
}