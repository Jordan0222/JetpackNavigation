package com.jordan.jetpacknavigation.presentation.button_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ButtonViewModel @Inject constructor(): ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun setMessage(text: String) {
        _message.postValue(text)
    }
}