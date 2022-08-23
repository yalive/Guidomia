package ca.bell.guidomia.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ca.bell.guidomia.common.addDivider
import ca.bell.guidomia.common.observe
import ca.bell.guidomia.common.viewBinding
import ca.bell.guidomia.databinding.ActivityMainBinding
import ca.bell.guidomia.di.ManualDI

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModels<MainViewModel> {
        ManualDI.mainViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ManualDI.initIfNecessary(applicationContext)
        setContentView(binding.root)
        binding.recyclerView.adapter = CarsAdapter()
        binding.recyclerView.addDivider(showLast = false)
        observeViewModel()
    }

    private fun observeViewModel() {
        observe(viewModel.cars) { cars ->
            val adapter = binding.recyclerView.adapter as CarsAdapter
            adapter.cars = cars
        }
    }
}