package com.example.carrismetropolitana.repositories

import androidx.lifecycle.MutableLiveData
import com.example.carrismetropolitana.data.CarrisMetropolitanaDao
import com.example.carrismetropolitana.model.db.FavoriteDbModel
import com.example.carrismetropolitana.repository.ICarrisMetropolitanaDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCarrisMetropolitanaRepository : ICarrisMetropolitanaDbRepository {

    private val listLines = mutableListOf<FavoriteDbModel>()
    private val observableLinesResponseDataItems = MutableLiveData<List<FavoriteDbModel>>(listLines)
    private var shouldReturnNetWorkError = false

    override fun getFavoritesWithFlow(): Flow<List<FavoriteDbModel>> {
        return flow {
            emit(listLines)
            kotlinx.coroutines.delay(100L)
        }
    }

    fun setShouldReturnNetWorkError(value: Boolean) {
        shouldReturnNetWorkError = value
    }

    private fun refreshFavorieteLiveData() {
        observableLinesResponseDataItems.postValue(listLines)
    }

    override fun getFavorites(): List<FavoriteDbModel> {
        return observableLinesResponseDataItems.value!!
    }

    override suspend fun getFavById(id: String): FavoriteDbModel {
        return observableLinesResponseDataItems.value!!.filter { id == it.id }[0]
    }

    override suspend fun insertFavorite(favoriteDbModel: FavoriteDbModel) {
        listLines.add(favoriteDbModel)
        refreshFavorieteLiveData()
    }

    override suspend fun updateFavorite(favoriteDbModel: FavoriteDbModel) {
        listLines.replaceAll { if (it.id == favoriteDbModel.id) favoriteDbModel else it}
        refreshFavorieteLiveData()
    }

    override suspend fun deleteAllFavorites() {
        listLines.clear()
        refreshFavorieteLiveData()
    }

    override suspend fun deleteFavorite(id: String) {
        listLines.removeIf {
            it.id == id
        }
        refreshFavorieteLiveData()
    }
}