package com.example.carrismetropolitana.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.carrismetropolitana.model.db.FavoriteDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CarrisMetropolitanaDao {

    @Query("SELECT * from fav_lines_tbl")
    fun getFavorites(): List<FavoriteDbModel>

    @Query("SELECT * from fav_lines_tbl")
    fun getFavoritesWithFlow(): Flow<List<FavoriteDbModel>>

    @Query("SELECT * from fav_lines_tbl where id = :id")
    suspend fun getFavById(id: String): FavoriteDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteDbModel: FavoriteDbModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favoriteDbModel: FavoriteDbModel)

    @Query("DELETE from fav_lines_tbl")
    suspend fun deleteAllFavorites()

    @Query("DELETE from fav_lines_tbl where id= :id")
    suspend fun deleteFavorite(id: String)
}