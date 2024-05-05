package com.example.carrismetropolitana.repositories

import androidx.lifecycle.MutableLiveData
import com.example.carrismetropolitana.model.db.FavoriteDbModel
import com.example.carrismetropolitana.repository.ICarrisMetropolitanaDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCarrisMetropolitanaRepository : ICarrisMetropolitanaDbRepository {

    private val favoriteItens = mutableListOf<FavoriteDbModel>()
    private val observableLinesResponseDataItems =
        MutableLiveData<List<FavoriteDbModel>>(favoriteItens)
    private var shouldReturnNetWorkError = false

    override fun getFavoritesWithFlow(): Flow<List<FavoriteDbModel>> {
        return flow {
            emit(favoriteItens.toList())
        }
    }

    fun setShouldReturnNetWorkError(value: Boolean) {
        shouldReturnNetWorkError = value
    }

    override fun getFavorites(): List<FavoriteDbModel> {
        return observableLinesResponseDataItems.value.orEmpty()
    }

    override suspend fun getFavById(id: String): FavoriteDbModel? {
        favoriteItens.let { list ->
            if (list.isEmpty()) {
                return null
            } else {
                val listResult = list.filter { id == it.id }
                if (listResult.isEmpty()) {
                    return null
                } else {
                    return listResult[0]
                }
            }
        }
    }

    override suspend fun insertFavorite(favoriteDbModel: FavoriteDbModel) {
        favoriteItens.add(favoriteDbModel)
    }

    override suspend fun updateFavorite(favoriteDbModel: FavoriteDbModel) {
        favoriteItens.replaceAll { if (it.id == favoriteDbModel.id) favoriteDbModel else it }
    }

    override suspend fun deleteAllFavorites() {
        favoriteItens.clear()
    }

    override suspend fun deleteFavorite(id: String) {
        favoriteItens.removeIf {
            it.id == id
        }
    }
}