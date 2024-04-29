package com.example.carrismetropolitana.screens.lineDetail.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SearchViewModel : ViewModel() {
    //first state whether the search is happening or not
    private val _searching = MutableStateFlow(false)
    val isSearching = _searching.asStateFlow()

    //second state the text typed by the user
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
}
