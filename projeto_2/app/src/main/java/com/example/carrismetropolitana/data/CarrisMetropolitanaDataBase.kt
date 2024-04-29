package com.example.carrismetropolitana.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.carrismetropolitana.model.Favorite
import com.example.carrismetropolitana.utils.Constants.DATA_BASE_VERSION

@Database(entities = [Favorite::class], version = DATA_BASE_VERSION, exportSchema = false)
abstract class CarrisMetropolitanaDataBase : RoomDatabase() {
    abstract fun carrisMetropolitanaDao() : CarrisMetropolitanaDao
}