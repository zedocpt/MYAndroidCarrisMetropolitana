package com.example.carrismetropolitana.screens.showLines

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.model.responseData.wrapper.LinesWrapper
import com.example.carrismetropolitana.screens.uiModel.favorite.LinesWrapperUiModel
import com.example.carrismetropolitana.screens.uiModel.favorite.toUIModel
import com.example.carrismetropolitana.usesCase.GetFavoriteList
import com.example.carrismetropolitana.usesCase.GetLineMatchWithFavorites
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowLinesViewModel @Inject constructor(
    private val getLineMatchWithFavorites: GetLineMatchWithFavorites,
    private val getFavoriteList: GetFavoriteList
) : ViewModel() {

    /***
     * list lines
     */

    private val _linesList: MutableState<DataOrException<List<LinesWrapperUiModel>, Boolean, Exception>> =
        mutableStateOf(DataOrException(arrayListOf(), true, null))
    val linesList: DataOrException<List<LinesWrapperUiModel>, Boolean, Exception>
        get() = _linesList.value

    private var favoritelistSize: MutableState<Int> = mutableStateOf(0)
    private var favoritelistFirstInteraction: MutableState<Boolean> = mutableStateOf(false)

    val linesFilterList: MutableState<ArrayList<LinesWrapperUiModel>> =
        mutableStateOf(arrayListOf())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getLineMatchWithFavorites().let { matchWithFavorites ->
                val list = matchWithFavorites.data?.map {
                    it.toUIModel()
                }

                _linesList.value =
                    DataOrException(list, matchWithFavorites.loading, matchWithFavorites.e)
                favoritelistFirstInteraction.value = true
            }

            getFavoriteList().distinctUntilChanged().collect { favoriteList ->
                val currentSize = favoriteList.size
                if (favoritelistSize.value != currentSize) {
                    favoritelistSize.value = currentSize
                    if (favoritelistFirstInteraction.value) {
                        getLineMatchWithFavorites().let { matchWithFavorites ->
                            val list = matchWithFavorites.data?.map {
                                it.toUIModel()
                            }
                            _linesList.value = DataOrException(
                                list,
                                matchWithFavorites.loading,
                                matchWithFavorites.e
                            )
                        }
                    }
                    if (_searchText.value.isNotEmpty()) {
                        onSearchTextChange(searchText.value)
                    }
                }
            }
        }
    }


    /***
     * for search
     */
    //first state whether the search is happening or not
    private val _searching = MutableStateFlow(false)
    val isSearching = _searching.asStateFlow()

    //second state the text typed by the user
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()


    fun onSearchTextChange(search: String) {
        viewModelScope.launch {
            _searchText.value = search
            val list = arrayListOf<LinesWrapperUiModel>()
            _linesList.value.data?.forEach { item ->
                if (item.doesMatchSearchQuery(search)) {
                    list.add(item)
                }
            }
            linesFilterList.value = list
        }
    }
}