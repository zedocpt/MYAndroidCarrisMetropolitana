package com.example.carrismetropolitana.screens.uiModel.favorite

import com.example.carrismetropolitana.model.db.FavoriteDbModel
import com.example.carrismetropolitana.model.responseData.wrapper.LinesWrapper
import com.example.carrismetropolitana.screens.uiModel.lines.LineUiModel
import com.example.carrismetropolitana.screens.uiModel.lines.toUiModel

class LinesWrapperUiModel(
    var lineUiModel: LineUiModel,
    var isFavorite: Boolean = false
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$lineUiModel.long_name",
            "$lineUiModel.short_name",
            "$lineUiModel.id"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

fun LinesWrapper.toUIModel() = LinesWrapperUiModel(
    lineUiModel = lineResponseData.toUiModel(),
    isFavorite = isFavorite
)

fun LinesWrapperUiModel.ToFavorite() = FavoriteDbModel(
    id = lineUiModel.id,
    long_name = lineUiModel.long_name,
    short_name = lineUiModel.short_name,
    text_color = lineUiModel.text_color,
    color = lineUiModel.color,
)