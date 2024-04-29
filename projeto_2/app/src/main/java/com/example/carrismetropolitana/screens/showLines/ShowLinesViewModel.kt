package com.example.carrismetropolitana.screens.showLines

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.model.responseData.lines.LineResponseData
import com.example.carrismetropolitana.model.responseData.lines.LinesResponseData
import com.example.carrismetropolitana.model.responseData.lines.LinesUiModel
import com.example.carrismetropolitana.repository.CarrisMetropolitanaRepository
import com.example.carrismetropolitana.screens.favorites.FavoriteViewModel
import com.example.carrismetropolitana.screens.showLines.entities.LinesWrapperUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowLinesViewModel @Inject constructor(private val repository: CarrisMetropolitanaRepository) :
    ViewModel() {

    /***
     * list lines
     */

    private val _linesList: MutableState<DataOrException<LinesResponseData, Boolean, Exception>> =
        mutableStateOf(DataOrException(LinesResponseData(), false, null))
    val linesList = _linesList.value

    private val _linesFilterList: MutableStateFlow<ArrayList<LineResponseData>> = MutableStateFlow(arrayListOf())
    var linesFilterList = _linesFilterList.asStateFlow()

    /***
     * for search
     */
    //first state whether the search is happening or not
    private val _searching = MutableStateFlow(false)
    val isSearching = _searching.asStateFlow()

    //second state the text typed by the user
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    /* var listSearches = searchText.combine(_linesListFlow) { text, lines->
         if (text.isBlank()){
             lines
         }else{
             lines.filter {
                 it.doesMatchSearchQuery(text)
             }
         }
     }.stateIn(
         viewModelScope,
         SharingStarted.WhileSubscribed(5000),
         _linesListFlow.value
     )*/

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLines().let { lines ->
                linesList.data = lines.data
            }
        }
    }

    suspend fun getLines(): DataOrException<LinesResponseData, Boolean, Exception> {
        return repository.getLines()
    }


    fun onSearchTextChange(search: String) {
        _searchText.value = search

        viewModelScope.launch {
            val list = arrayListOf<LineResponseData>()
            _linesList.value.data?.forEach { item ->
                if (item.short_name.contains(search) ||
                    item.long_name.contains(search)
                ){
                    list.add(item)
                }
            }
            _linesFilterList.value = list
        }
    }
}