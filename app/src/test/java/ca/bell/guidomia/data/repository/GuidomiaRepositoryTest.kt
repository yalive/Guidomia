package ca.bell.guidomia.data.repository

import ca.bell.guidomia.data.Car
import ca.bell.guidomia.data.local.CarLocalDataSource
import ca.bell.guidomia.data.remote.CarRemoteDataSource
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
internal class GuidomiaRepositoryTest {

    private val localDataSource = mockk<CarLocalDataSource>()
    private val remoteDataSource = mockk<CarRemoteDataSource>()

    private val repository = GuidomiaRepository(localDataSource, remoteDataSource)

    @Test
    fun `When car list already exist in database, remote data source shouldn't be called`() =
        runTest {
            // Given
            coEvery { localDataSource.getCarList() } returns cars

            // When
            val carList = repository.getCars()

            // Then: Remote data source will not be called
            assertEquals(carList, cars)
            coVerify { remoteDataSource wasNot Called }
        }

    @Test
    fun `When database is empty, remote data source called, and returned list is saved in database`() =
        runTest {
            // Given
            coEvery { localDataSource.getCarList() } returns emptyList() andThen cars
            coEvery { remoteDataSource.getCarList() } returns cars
            coEvery { localDataSource.saveCars(any()) } returns Unit

            // When
            val carList = repository.getCars()

            // Then: Remote data source will not be called
            assertEquals(carList, cars)
            coVerify { localDataSource.saveCars(cars) }
        }
}


private const val MAKE_1 = "Make 1"
private const val MAKE_2 = "Make 2"

private const val MODEL_1_1 = "Model 11"
private const val MODEL_1_2 = "Model 12"
private const val MODEL_2_1 = "Model 21"
private const val MODEL_2_2 = "Model 22"

private val cars = listOf(
    Car(
        consList = emptyList(),
        customerPrice = 10.toBigDecimal(),
        make = MAKE_1,
        marketPrice = 10.toBigDecimal(),
        model = MODEL_1_1,
        prosList = listOf(),
        rating = 4
    ),
    Car(
        consList = emptyList(),
        customerPrice = 10.toBigDecimal(),
        make = MAKE_1,
        marketPrice = 10.toBigDecimal(),
        model = MODEL_1_2,
        prosList = listOf(),
        rating = 4
    ),
    Car(
        consList = emptyList(),
        customerPrice = 10.toBigDecimal(),
        make = MAKE_2,
        marketPrice = 10.toBigDecimal(),
        model = MODEL_2_1,
        prosList = listOf(),
        rating = 4
    ),
    Car(
        consList = emptyList(),
        customerPrice = 10.toBigDecimal(),
        make = MAKE_2,
        marketPrice = 10.toBigDecimal(),
        model = MODEL_2_2,
        prosList = listOf(),
        rating = 4
    )
)