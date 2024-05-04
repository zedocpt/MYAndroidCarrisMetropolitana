package com.example.carrismetropolitana.repository

import com.example.carrismetropolitana.data.CarrisMetropolitanaDao
import com.example.carrismetropolitana.model.db.FavoriteDbModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarrisMetropolitanaDbRepository @Inject constructor(private val  carrisMetropolitanaDao : CarrisMetropolitanaDao) : ICarrisMetropolitanaDbRepository {

    fun getFavorite() : Flow<List<FavoriteDbModel>> = carrisMetropolitanaDao.getFavoritesWithFlow()
    override fun getFavorites(): List<FavoriteDbModel> {
        return carrisMetropolitanaDao.getFavorites()
    }

    override fun getFavoritesWithFlow(): Flow<List<FavoriteDbModel>> {
        return carrisMetropolitanaDao.getFavoritesWithFlow()
    }

    override suspend fun getFavById(id: String): FavoriteDbModel {
       return carrisMetropolitanaDao.getFavById(id)
    }

    override suspend fun insertFavorite(favoriteDbModel: FavoriteDbModel) = carrisMetropolitanaDao.insertFavorite(favoriteDbModel)
    override suspend fun updateFavorite(favoriteDbModel: FavoriteDbModel) =carrisMetropolitanaDao.updateFavorite(favoriteDbModel)
    override suspend fun deleteAllFavorites() = carrisMetropolitanaDao.deleteAllFavorites()
    override suspend fun deleteFavorite(favoriteId: String) = carrisMetropolitanaDao.deleteFavorite(favoriteId)
    suspend fun getFavoriteById(id : String) : FavoriteDbModel = carrisMetropolitanaDao.getFavById(id)
}