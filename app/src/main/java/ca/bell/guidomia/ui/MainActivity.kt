package ca.bell.guidomia.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ca.bell.guidomia.common.*
import ca.bell.guidomia.databinding.ActivityMainBinding
import ca.bell.guidomia.di.injector


class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModels<MainViewModel> {
        injector.mainViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        observeViewModel()
    }

    private fun setupView() = with(binding) {
        recyclerView.adapter = CarsAdapter(onClickCar = { car ->
            viewModel.onClickCar(car)
        })
        recyclerView.addDivider(showLast = false)

        makeSpinner.doOnItemSelected { position ->
            viewModel.onSelectMake(position)
        }

        modelSpinner.doOnItemSelected { position ->
            val currentMakePosition = makeSpinner.selectedItemPosition
            viewModel.onSelectModel(position, currentMakePosition)
        }
    }

    private fun observeViewModel() {
        observeEvent(viewModel.loading) { loading ->
            binding.progressBar.isVisible = loading
        }

        observe(viewModel.cars) { cars ->
            val adapter = binding.recyclerView.adapter as CarsAdapter
            adapter.submitList(cars)
        }

        observe(viewModel.carMakers) { makers ->
            binding.makeSpinner.adapter = SpinnerArrayAdapter(this, makers)
        }

        observe(viewModel.carModels) { models ->
            binding.modelSpinner.adapter = SpinnerArrayAdapter(this, models)
        }
    }
}