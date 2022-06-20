package com.jordan.jetpacknavigation.presentation.pager_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jordan.jetpacknavigation.domain.Datasource
import com.jordan.jetpacknavigation.domain.model.Intro
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagerViewModel @Inject constructor(): ViewModel() {

    private val _introItems = MutableLiveData<List<Intro>>()
    val introItems: LiveData<List<Intro>> = _introItems

    init {
        // Initialize data.
        val myDataset = Datasource().loadIntroList()

        _introItems.postValue(myDataset)
    }
}