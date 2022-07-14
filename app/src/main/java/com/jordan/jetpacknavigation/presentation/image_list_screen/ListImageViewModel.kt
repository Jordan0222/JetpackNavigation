package com.jordan.jetpacknavigation.presentation.image_list_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jordan.jetpacknavigation.data.source.Datasource
import com.jordan.jetpacknavigation.domain.model.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListImageViewModel @Inject constructor(): ViewModel() {

    private val _listItems = MutableLiveData<List<ListItem>>()
    val listItems: LiveData<List<ListItem>> = _listItems

    init {
        // Initialize data.
        val myDataset = Datasource().loadAffirmations()

        _listItems.postValue(myDataset)
    }
}