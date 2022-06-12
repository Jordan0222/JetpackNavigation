package com.jordan.jetpacknavigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.NumberFormat
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _tipResult = MutableLiveData<String>()
    val tipResult: LiveData<String> = _tipResult

    fun calculateTip(
        stringInTextField: String,
        tipPercentage: Double,
        roundUpSwitchIsChecked: Boolean
    ) {
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            _tipResult.postValue("cost is null")
            return
        }

        var tip = tipPercentage * cost
        if (roundUpSwitchIsChecked) {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        _tipResult.postValue(formattedTip)
    }
}