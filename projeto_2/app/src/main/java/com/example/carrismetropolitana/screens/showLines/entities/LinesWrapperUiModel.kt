package com.example.carrismetropolitana.screens.showLines.entities

import com.example.carrismetropolitana.model.Favorite
import com.example.carrismetropolitana.model.responseData.lines.LineResponseData

data class LinesWrapperUiModel(
    var lineResponseData: LineResponseData,
    var isFavorite: Boolean = false
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$lineResponseData.long_name",
            "$lineResponseData.short_name",
            "$lineResponseData.id"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

fun LinesWrapperUiModel.ToFavorite() = Favorite(
    id = lineResponseData.id,
    long_name = lineResponseData.long_name,
    short_name = lineResponseData.short_name,
    text_color = lineResponseData.text_color,
    color = lineResponseData.color,
)

