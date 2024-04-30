package com.example.carrismetropolitana.dataSource

import androidx.room.Query
import com.example.carrismetropolitana.data.CarrisMetropolitanaDao
import com.example.carrismetropolitana.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDbDataSource @Inject constructor(private val  carrisMetropolitanaDao : CarrisMetropolitanaDao) {

  suspend fun getFavoritesSimple() : List<Favorite> = carrisMetropolitanaDao.getFavoritesSimple()

  fun getFavorites() : Flow<List<Favorite>>  = carrisMetropolitanaDao.getFavorites()
}