package com.example.carrismetropolitana.dataSource

import com.example.carrismetropolitana.data.CarrisMetropolitanaDao
import com.example.carrismetropolitana.model.db.FavoriteDbModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDbDataSource @Inject constructor(private val  carrisMetropolitanaDao : CarrisMetropolitanaDao) {

  suspend fun getFavoritesSimple() : List<FavoriteDbModel> = carrisMetropolitanaDao.getFavoritesSimple()

  fun getFavorites() : Flow<List<FavoriteDbModel>>  = carrisMetropolitanaDao.getFavorites()
}