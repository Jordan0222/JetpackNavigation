package com.jordan.jetpacknavigation.presentation.word_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jordan.jetpacknavigation.domain.Datasource
import com.jordan.jetpacknavigation.domain.model.WordItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(): ViewModel() {

    private val _wordItems = MutableLiveData<List<WordItem>>()
    val wordItems: LiveData<List<WordItem>> = _wordItems

    private val _isLinearLayoutManager = MutableStateFlow(true)
    val isLinearLayoutManager = _isLinearLayoutManager.asStateFlow()

    var linearState = mutableStateOf(true)

    init {
        // Initialize data.
        val myDataset = Datasource().loadLetters()

        _wordItems.postValue(myDataset)
    }

    fun layoutManagerChange() {
        _isLinearLayoutManager.value = !_isLinearLayoutManager.value
        linearState.value = !linearState.value
    }
}