package com.example.carrismetropolitana.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.carrismetropolitana.uiModel.favorite.FavoriteUiModel
import javax.annotation.Nonnull

@Entity(tableName = "fav_lines_tbl")
data class FavoriteDbModel(
    @Nonnull
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    val long_name: String,
    val short_name: String,
    val text_color: String,
    val color: String,
)

fun FavoriteDbModel.toUiMode() = FavoriteUiModel(
    id = id,
    long_name = long_name,
    short_name = short_name,
    text_color = text_color,
    color = color
)
