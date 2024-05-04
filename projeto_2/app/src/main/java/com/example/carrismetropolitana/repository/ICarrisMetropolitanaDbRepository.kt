package com.example.carrismetropolitana.repository

import com.example.carrismetropolitana.model.db.FavoriteDbModel
import kotlinx.coroutines.flow.Flow

interface ICarrisMetropolitanaDbRepository {

    fun getFavorites(): List<FavoriteDbModel>

    fun getFavoritesWithFlow(): Flow<List<FavoriteDbModel>>

    suspend fun getFavById(id: String): FavoriteDbModel

    suspend fun insertFavorite(favoriteDbModel: FavoriteDbModel)

    suspend fun updateFavorite(favoriteDbModel: FavoriteDbModel)

    suspend fun deleteAllFavorites()

    suspend fun deleteFavorite(id: String)
}