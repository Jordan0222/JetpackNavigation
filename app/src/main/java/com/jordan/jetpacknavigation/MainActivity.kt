package com.jordan.jetpacknavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jordan.jetpacknavigation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {

            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }

            val isChecked = binding.roundUpSwitch.isChecked

            viewModel.calculateTip(
                binding.costOfService.text.toString(),
                tipPercentage,
                isChecked
            )
        }

        subscribeToObservables()
    }

    private fun subscribeToObservables() {
        viewModel.tipResult.observe(this) {
            binding.tipResult.text = it
        }
    }
}