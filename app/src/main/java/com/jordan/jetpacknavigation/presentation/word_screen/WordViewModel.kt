package com.jordan.jetpacknavigation.presentation.word_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jordan.jetpacknavigation.domain.Datasource
import com.jordan.jetpacknavigation.domain.model.ListItem
import com.jordan.jetpacknavigation.domain.model.WordItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(): ViewModel() {

    private val _wordItems = MutableLiveData<List<WordItem>>()
    val wordItems: LiveData<List<WordItem>> = _wordItems

    init {
        // Initialize data.
        val myDataset = Datasource().loadLetters()

        _wordItems.postValue(myDataset)
    }
}