package ca.bell.guidomia.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.bell.guidomia.R
import ca.bell.guidomia.common.viewBinding
import ca.bell.guidomia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val cars = listOf(
            CarUiModel(
                name = "Range Rover",
                price = "Price: 125K",
                rating = 4,
                image = R.drawable.range_rover
            ),
            CarUiModel(
                name = "BMW 330i",
                price = "Price: 125K",
                rating = 3,
                image = R.drawable.range_rover
            ),
            CarUiModel(
                name = "Mercedez benz GLC",
                price = "Price: 125K",
                rating = 4,
                image = R.drawable.range_rover
            ),
            CarUiModel(
                name = "Alpine Roadster",
                price = "Price: 125K",
                rating = 5,
                image = R.drawable.range_rover
            )
        )
        binding.recyclerView.adapter = CarsAdapter(cars)
    }
}