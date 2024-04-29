package com.example.carrismetropolitana.screens.showLines.entities

import com.example.carrismetropolitana.model.Favorite
import com.example.carrismetropolitana.model.responseData.lines.LineResponseData

data class LinesWrapperUiModel(var lineResponseData: LineResponseData, var isFavorite : Boolean = false)

fun LinesWrapperUiModel.ToFavorite() = Favorite(
    id = lineResponseData.id,
    long_name = lineResponseData.long_name,
    short_name = lineResponseData.short_name,
    text_color = lineResponseData.text_color,
    color = lineResponseData.color,
)