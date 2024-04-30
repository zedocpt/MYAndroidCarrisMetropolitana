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
    fun getFavoritesSimple() : List<FavoriteDbModel> //todo melhorar nome

    @Query("SELECT * from fav_lines_tbl")
    fun getFavorites() : Flow<List<FavoriteDbModel>>
    @Query("SELECT * from fav_lines_tbl where id = :favoriteId")
    suspend fun getFavById(favoriteId : String) : FavoriteDbModel
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteDbModel: FavoriteDbModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favoriteDbModel: FavoriteDbModel)

    @Query("DELETE from fav_lines_tbl")
    suspend fun deleteAllFavorites()

    //@Delete ("DELETE from fav_lines_tbl where id = :id")
    @Query("DELETE from fav_lines_tbl where id= :favoriteId")
    suspend fun deleteFavorite(favoriteId: String)
}