package com.jordan.jetpacknavigation.presentation.gallery_screen

import androidx.lifecycle.ViewModel
import com.jordan.jetpacknavigation.domain.Datasource
import com.jordan.jetpacknavigation.domain.model.Intro
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(): ViewModel() {

    private val _cloudItems = MutableStateFlow<List<Intro>>(emptyList())
    val cloudItems = _cloudItems.asStateFlow()

    private val _mountainItems = MutableStateFlow<List<Intro>>(emptyList())
    val mountainItems = _mountainItems.asStateFlow()

    private val _sunshineItems = MutableStateFlow<List<Intro>>(emptyList())
    val sunshineItems = _sunshineItems.asStateFlow()

    init {
        loadCloud()
        loadMountain()
        loadSunshine()
    }

    private fun loadCloud() {
        val dataSet = Datasource().loadCloud()
        _cloudItems.value = dataSet
    }

    private fun loadMountain() {
        val dataSet = Datasource().loadMountain()
        _mountainItems.value = dataSet
    }

    private fun loadSunshine() {
        val dataSet = Datasource().loadSunshine()
        _sunshineItems.value = dataSet
    }
}