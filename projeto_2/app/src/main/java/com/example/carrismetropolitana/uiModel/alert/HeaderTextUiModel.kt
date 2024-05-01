package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.HeaderTextResponseData

data class HeaderTextUiModel(
    val translationUiModel: List<TranslationUiModel>
)

fun HeaderTextResponseData.toUiModel() = HeaderTextUiModel(
    translationUiModel = translationResponseData.map { it.toUiModel() },

    )