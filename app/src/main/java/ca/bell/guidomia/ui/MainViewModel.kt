package ca.bell.guidomia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.bell.guidomia.common.event.Event
import ca.bell.guidomia.common.event.trigger
import ca.bell.guidomia.data.repository.GuidomiaRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: GuidomiaRepository
) : ViewModel() {

    private val _cars = MutableLiveData<List<CarUiModel>>()
    val cars: LiveData<List<CarUiModel>> get() = _cars

    private val _carMakes = MutableLiveData<List<String>>()
    val carMakes: LiveData<List<String>> get() = _carMakes

    private val _makeModels = MutableLiveData<List<String>>()
    val makeModels: LiveData<List<String>> get() = _makeModels

    private val _loading = MutableLiveData<Event<Boolean>>()
    val loading: LiveData<Event<Boolean>> get() = _loading

    private var allCars = emptyList<CarUiModel>()

    private val modelsByMake = mutableMapOf<String, List<String>>()

    init {
        _carMakes.value = listOf(ANY_MAKE)
        _makeModels.value = listOf(ANY_MODEL)
        prepareUiCars()
    }


    fun onClickCar(car: CarUiModel) {
        allCars = allCars.map { it.copy(expanded = car.identifier == it.identifier) }
        _cars.value = cars.value?.map { uiModel ->
            uiModel.copy(expanded = car.identifier == uiModel.identifier)
        }
    }

    fun onSelectMake(position: Int) {
        // Find selected make
        val make = _carMakes.value.orEmpty().getOrNull(position) ?: return

        // Get all models of the selected make
        val models = modelsByMake[make]
        _makeModels.value = when {
            models.isNullOrEmpty() -> listOf(ANY_MODEL)
            else -> models
        }

        // Update car list with current make
        _cars.value = when (make) {
            ANY_MAKE -> allCars
            else -> allCars.filter { it.make == make }
        }
    }

    fun onSelectModel(position: Int, selectedMakePosition: Int) {
        // Find selected model
        val model = _makeModels.value.orEmpty().getOrNull(position) ?: return
        val make = _carMakes.value.orEmpty().getOrNull(selectedMakePosition) ?: return

        val makeCars = when (make) {
            ANY_MAKE -> allCars
            else -> allCars.filter { it.make == make }
        }

        val modelCars = when (model) {
            ANY_MODEL -> makeCars
            else -> makeCars.filter { it.name == model }
        }

        // Update car list with current make and model
        _cars.value = modelCars
    }

    private fun prepareUiCars() = viewModelScope.launch {
        _loading.trigger(true)
        val carList = repository.getCars()
        val carUiModels = carList.mapIndexed { index, car ->
            CarUiModel(car = car, expanded = index == 0)
        }
        allCars = carUiModels
        _cars.value = carUiModels
        initFilters()
        _loading.trigger(false)
    }

    private fun initFilters() {
        // Group by make
        val allMaks = allCars.groupBy { it.make }

        // Order of insertion will be preserved (Linked Hash map)
        modelsByMake[ANY_MAKE] = listOf(ANY_MODEL)
        allMaks.forEach { entry ->
            val make = entry.key
            val models = entry.value.map { it.name }.distinct().toMutableList()
            models.add(0, ANY_MODEL)
            modelsByMake[make] = models
        }

        // Make list
        _carMakes.value = modelsByMake.keys.toList()

        // No make is selected, so there is no model to choose
        _makeModels.value = listOf(ANY_MODEL)
    }

    companion object {
        const val ANY_MAKE = "Any make"
        const val ANY_MODEL = "Any model"
    }
}