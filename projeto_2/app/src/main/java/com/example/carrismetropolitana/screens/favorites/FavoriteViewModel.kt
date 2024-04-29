package com.example.carrismetropolitana.screens.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrismetropolitana.model.Favorite
import com.example.carrismetropolitana.model.responseData.lines.LinesResponseData
import com.example.carrismetropolitana.repository.CarrisMetropolitanaDbRepository
import com.example.carrismetropolitana.screens.showLines.entities.LinesWrapperUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: CarrisMetropolitanaDbRepository) :
    ViewModel() {

    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    private var _linesWrapperListData = MutableStateFlow<List<LinesWrapperUiModel>>(arrayListOf())
    val linesWrapperListData = _linesWrapperListData.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorite().distinctUntilChanged().collect { listOfFavs ->
                if (listOfFavs.isEmpty()) {
                    Log.d("TAG", ":Empty favs")
                } else {
                    Log.d("TAG", ":${favList.value}")
                    _favList.value = listOfFavs
                }
            }
        }
    }

    fun insertFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.insertFavorite(favorite)
    }

    fun getFavoriteById(id: String) = viewModelScope.launch {
        repository.getFavoriteById(id)
    }

    fun updateFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.updateFavorite(favorite)
    }

    fun deleteAllFavorites() = viewModelScope.launch {
        repository.deleteAllFavorites()
    }

    fun deleteFavorite(favoriteId: String) = viewModelScope.launch {
        repository.deleteFavorite(favoriteId)
    }

    /* fun onSearchQueryChange(query: String) {
         state = state.copy(searchQuery = query)

         searchJob?.cancel()
         searchJob = viewModelScope.launch {
             delay(500L)
             val todos = todoSearchManager.searchTodos(query)
             state = state.copy(todos = todos)
         }
     }*/

    fun mappingFavoriteList(linesResponseData: LinesResponseData?) {
        viewModelScope.launch(Dispatchers.IO) {
            linesResponseData?.let {
                _linesWrapperListData.value = linesResponseData.map { line ->
                    if (favList.value.isNotEmpty()) {
                        val favorite = favList.value.find { it -> it.id == line.id }
                        if (favorite != null) {
                            LinesWrapperUiModel(line, true)
                        } else {
                            LinesWrapperUiModel(line, false)
                        }
                    } else {
                        LinesWrapperUiModel(line, false)
                    }
                }
            }
        }
    }
}