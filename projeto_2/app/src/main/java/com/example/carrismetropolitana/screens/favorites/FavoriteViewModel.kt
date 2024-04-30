package com.example.carrismetropolitana.screens.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrismetropolitana.model.db.FavoriteDbModel
import com.example.carrismetropolitana.model.db.toUiMode
import com.example.carrismetropolitana.screens.uiModel.favorite.FavoriteUiModel
import com.example.carrismetropolitana.repository.CarrisMetropolitanaDbRepository
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

    private val _favList = MutableStateFlow<List<FavoriteUiModel>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorite().distinctUntilChanged().collect { listOfFavs ->
                if (listOfFavs.isEmpty()) {
                    Log.d("TAG", ":Empty favs")
                    _favList.value = arrayListOf()
                } else {
                    Log.d("TAG", ":${favList.value}")
                    _favList.value = listOfFavs.map { it.toUiMode() }
                }
            }
        }
    }

    fun insertFavorite(favoriteDbModel: FavoriteDbModel) = viewModelScope.launch {
        repository.insertFavorite(favoriteDbModel)
    }

    fun getFavoriteById(id: String) = viewModelScope.launch {
        repository.getFavoriteById(id)
    }

    fun updateFavorite(favoriteDbModel: FavoriteDbModel) = viewModelScope.launch {
        repository.updateFavorite(favoriteDbModel)
    }

    fun deleteAllFavorites() = viewModelScope.launch {
        repository.deleteAllFavorites()
    }

    fun deleteFavorite(favoriteId: String) = viewModelScope.launch {
        repository.deleteFavorite(favoriteId)
    }
}