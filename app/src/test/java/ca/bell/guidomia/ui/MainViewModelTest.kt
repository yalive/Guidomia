package ca.bell.guidomia.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ca.bell.guidomia.MainDispatcherRule
import ca.bell.guidomia.data.Car
import ca.bell.guidomia.data.repository.GuidomiaRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<GuidomiaRepository>()

    @Test
    fun `Cars loaded and first one is expanded`() {
        // Given
        coEvery { repository.getCars() } returns cars

        // When: default init of viewModel
        val viewModel = MainViewModel(repository)

        // Then: cars loaded and first
        assert(viewModel.cars.value!!.first().expanded)
        assert(!viewModel.cars.value!![1].expanded)
    }

    @Test
    fun `By default make filter contains all makes, and model filter contains only Any model`() {
        // Given
        coEvery { repository.getCars() } returns cars

        // When: default init of viewModel
        val viewModel = MainViewModel(repository)

        // Then: cars loaded and first
        assertEquals(viewModel.carMakes.value, listOf(ANY_MAKE, MAKE_1, MAKE_2))
        assertEquals(viewModel.carModels.value, listOf(ANY_MODEL))
    }

    @Test
    fun `When click a car it should expand, and the previously expanded should collapse`() {
        // Given
        coEvery { repository.getCars() } returns cars
        val viewModel = MainViewModel(repository)

        // When
        val clickedCarPosition = 1
        viewModel.onClickCar(viewModel.cars.value!![clickedCarPosition])

        // Then: the car should expand and previous should collapse
        viewModel.cars.value!!.forEachIndexed { index, carUiModel ->
            assertEquals(carUiModel.expanded, clickedCarPosition == index)
        }
    }

    @Test
    fun `On select a make, the car list should contains this make cars only, also model filter contains selected make models`() {
        // Given
        coEvery { repository.getCars() } returns cars
        val viewModel = MainViewModel(repository)

        val selectedMake = MAKE_1
        val selectedMakeModels = listOf(ANY_MODEL, MODEL_1_1, MODEL_1_2)

        // When
        viewModel.onSelectMake(1)

        // Then: Car list contains only selected make cars
        assertEquals(viewModel.cars.value!!.size, 2)
        viewModel.cars.value!!.forEach {
            assertEquals(it.make, selectedMake)
        }
        assertEquals(viewModel.cars.value!![0].name, MODEL_1_1)
        assertEquals(viewModel.cars.value!![1].name, MODEL_1_2)

        // Also model list contains only selected make models
        assertEquals(viewModel.carModels.value, selectedMakeModels)
    }

    @Test
    fun `On select a model of a given make, the car list should contains only this model`() {
        // Given
        coEvery { repository.getCars() } returns cars
        val viewModel = MainViewModel(repository)

        val selectedMake = MAKE_2

        // When
        viewModel.onSelectMake(2)
        viewModel.onSelectModel(position = 1, selectedMakePosition = 2)

        // Then: Car list contains only selected make cars
        assertEquals(viewModel.cars.value!!.size, 1)
        assertEquals(viewModel.cars.value!![0].make, selectedMake)
        assertEquals(viewModel.cars.value!![0].name, MODEL_2_1)
    }
}

private const val ANY_MAKE = "Any make"
private const val MAKE_1 = "Make 1"
private const val MAKE_2 = "Make 2"

private const val ANY_MODEL = "Any model"
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