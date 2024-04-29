package com.example.carrismetropolitana.repository

import com.example.carrismetropolitana.data.CarrisMetropolitanaDao
import com.example.carrismetropolitana.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarrisMetropolitanaDbRepository @Inject constructor(private val  carrisMetropolitanaDao : CarrisMetropolitanaDao) {

    fun getFavorite() : Flow<List<Favorite>> = carrisMetropolitanaDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite) = carrisMetropolitanaDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite) =carrisMetropolitanaDao.updateFavorite(favorite)
    suspend fun deleteAllFavorites() = carrisMetropolitanaDao.deleteAllFavorites()
    suspend fun deleteFavorite(favoriteId: String) = carrisMetropolitanaDao.deleteFavorite(favoriteId)
    suspend fun getFavoriteById(favoriteId : String) : Favorite = carrisMetropolitanaDao.getFavById(favoriteId)
}