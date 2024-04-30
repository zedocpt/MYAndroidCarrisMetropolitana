package com.example.carrismetropolitana.screens.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.UrlResponseData

data class UrlUiModel(
    val translationUiModel: List<TranslationUiModel>
)

fun UrlResponseData.toUiModel() = UrlUiModel(
    translationUiModel = translationResponseData.map {
        it.toUiModel()
    }
)