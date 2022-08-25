package ca.bell.guidomia.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ca.bell.guidomia.R
import ca.bell.guidomia.common.*
import ca.bell.guidomia.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        observeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_main) {
            Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)
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

        observe(viewModel.carMakes) { makes ->
            binding.makeSpinner.adapter = SpinnerArrayAdapter(this, makes)
        }

        observe(viewModel.carModels) { models ->
            binding.modelSpinner.adapter = SpinnerArrayAdapter(this, models)
        }
    }
}