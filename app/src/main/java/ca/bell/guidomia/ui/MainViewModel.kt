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

    private val _carMakers = MutableLiveData<List<String>>()
    val carMakers: LiveData<List<String>> get() = _carMakers

    private val _carModels = MutableLiveData<List<String>>()
    val carModels: LiveData<List<String>> get() = _carModels

    private val _loading = MutableLiveData<Event<Boolean>>()
    val loading: LiveData<Event<Boolean>> get() = _loading

    private var allCars = emptyList<CarUiModel>()

    private val modelsByMaker = mutableMapOf<String, List<String>>()

    init {
        prepareUiCars()

        _carMakers.value = listOf(ANY_MAKE)
        _carModels.value = listOf(ANY_MODEL)
    }


    fun onClickCar(car: CarUiModel) {
        allCars = allCars.map { it.copy(expanded = car.identifier == it.identifier) }
        _cars.value = cars.value?.map { uiModel ->
            uiModel.copy(expanded = car.identifier == uiModel.identifier)
        }
    }

    fun onSelectMake(position: Int) {
        // Find selected make
        val make = _carMakers.value.orEmpty().getOrNull(position) ?: return

        // Get all models of the selected make
        _carModels.value = modelsByMaker[make].orEmpty().toMutableList()

        // Update car list with current make
        _cars.value = when (make) {
            ANY_MAKE -> allCars
            else -> allCars.filter { it.make == make }
        }
    }

    fun onSelectModel(position: Int, selectedMakePosition: Int) {
        // Find selected model
        val model = _carModels.value.orEmpty().getOrNull(position) ?: return
        val make = _carMakers.value.orEmpty().getOrNull(selectedMakePosition) ?: return

        val makerCars = when (make) {
            ANY_MAKE -> allCars
            else -> allCars.filter { it.make == make }
        }

        val modelCars = when (model) {
            ANY_MODEL -> makerCars
            else -> makerCars.filter { it.name == model }
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
        modelsByMaker[ANY_MAKE] = listOf(ANY_MODEL)
        allMaks.forEach { entry ->
            val make = entry.key
            val models = entry.value.map { it.name }.distinct().toMutableList()
            models.add(0, ANY_MODEL)
            modelsByMaker[make] = models
        }

        // Make list
        _carMakers.value = modelsByMaker.keys.toList()

        // No make is selected, so there is no model to choose
        _carModels.value = listOf(ANY_MODEL)
    }

    companion object {
        const val ANY_MAKE = "Any make"
        const val ANY_MODEL = "Any model"
    }
}