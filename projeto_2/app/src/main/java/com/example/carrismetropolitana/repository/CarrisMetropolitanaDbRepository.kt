package com.example.carrismetropolitana.repository

import com.example.carrismetropolitana.data.CarrisMetropolitanaDao
import com.example.carrismetropolitana.model.db.FavoriteDbModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarrisMetropolitanaDbRepository @Inject constructor(private val  carrisMetropolitanaDao : CarrisMetropolitanaDao) {

    fun getFavorite() : Flow<List<FavoriteDbModel>> = carrisMetropolitanaDao.getFavorites()
    suspend fun insertFavorite(favoriteDbModel: FavoriteDbModel) = carrisMetropolitanaDao.insertFavorite(favoriteDbModel)
    suspend fun updateFavorite(favoriteDbModel: FavoriteDbModel) =carrisMetropolitanaDao.updateFavorite(favoriteDbModel)
    suspend fun deleteAllFavorites() = carrisMetropolitanaDao.deleteAllFavorites()
    suspend fun deleteFavorite(favoriteId: String) = carrisMetropolitanaDao.deleteFavorite(favoriteId)
    suspend fun getFavoriteById(favoriteId : String) : FavoriteDbModel = carrisMetropolitanaDao.getFavById(favoriteId)
}