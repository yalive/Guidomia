package ca.bell.guidomia.ui

import androidx.lifecycle.*
import ca.bell.guidomia.data.GuidomiaRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: GuidomiaRepository
) : ViewModel() {

    private val _cars = MutableLiveData<List<CarUiModel>>()
    val cars: LiveData<List<CarUiModel>> get() = _cars

    init {
        prepareCars()
    }

    private fun prepareCars() = viewModelScope.launch {
        val carList = repository.getCarsList()
        val carUiModels = carList.mapIndexed { index, car ->
            CarUiModel(car = car, expanded = index == 0)
        }
        _cars.value = carUiModels
    }

    fun onClickCar(car: CarUiModel) {
        _cars.value = cars.value?.map {
            it.copy(expanded = car.identifier == it.identifier)
        }
    }


    class Factory(
        private val repository: GuidomiaRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}