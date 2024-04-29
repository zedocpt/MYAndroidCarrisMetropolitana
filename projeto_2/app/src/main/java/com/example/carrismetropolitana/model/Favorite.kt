package com.example.carrismetropolitana.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "fav_lines_tbl")
data class Favorite(
    @Nonnull
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    val long_name: String,
    val short_name: String,
    val text_color: String,
    val color: String,
)
