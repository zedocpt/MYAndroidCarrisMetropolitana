package com.example.carrismetropolitana.screens.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrismetropolitana.model.db.FavoriteDbModel
import com.example.carrismetropolitana.model.db.toUiMode
import com.example.carrismetropolitana.uiModel.favorite.FavoriteUiModel
import com.example.carrismetropolitana.repository.CarrisMetropolitanaDbRepository
import com.example.carrismetropolitana.repository.ICarrisMetropolitanaDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val repository: ICarrisMetropolitanaDbRepository
) :
    ViewModel() {

    private val _favList = MutableStateFlow<List<FavoriteUiModel>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            repository.getFavoritesWithFlow().distinctUntilChanged().collect { listOfFavs ->
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

    fun insertFavorite(favoriteDbModel: FavoriteDbModel) = viewModelScope.launch(dispatcher) {
        repository.insertFavorite(favoriteDbModel)
    }

    suspend fun getFavoriteById(id: String): FavoriteDbModel {
        var favorite: FavoriteDbModel? = null
        viewModelScope.launch(dispatcher) {
            favorite = repository.getFavById(id)
        }.join() // Esperar até que a coroutine seja concluída
        return favorite!!
    }

    suspend fun getFavoriteByIdV2(id: String): FavoriteDbModel {
       return repository.getFavById(id)
    }

    suspend fun updateFavorite(favoriteDbModel: FavoriteDbModel) = viewModelScope.launch(dispatcher) {
        repository.updateFavorite(favoriteDbModel)
    }

    suspend fun deleteAllFavorites() = viewModelScope.launch {
        repository.deleteAllFavorites()
    }

    fun deleteFavorite(favoriteId: String) = viewModelScope.launch(dispatcher) {
        repository.deleteFavorite(favoriteId)
    }
}