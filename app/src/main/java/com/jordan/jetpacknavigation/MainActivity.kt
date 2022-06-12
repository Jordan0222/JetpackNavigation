package com.jordan.jetpacknavigation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.TextUnit
import androidx.navigation.NavController
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

        binding.composeCalculateButton.apply {
            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }

            val isChecked = binding.roundUpSwitch.isChecked

            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Button(
                    onClick = {
                        viewModel.calculateTip(
                            binding.costOfServiceEditText.text.toString(),
                            tipPercentage,
                            isChecked
                        )
                    }
                ) {
                    Text(text = "Calculate")
                }
            }
        }

        binding.calculateButton.setOnClickListener {

            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }

            val isChecked = binding.roundUpSwitch.isChecked

            viewModel.calculateTip(
                binding.costOfServiceEditText.text.toString(),
                tipPercentage,
                isChecked
            )
        }

        binding.costOfService.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }

        subscribeToObservables()
    }

    private fun subscribeToObservables() {
        viewModel.tipResult.observe(this) {
            binding.tipResult.text = it
        }
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}