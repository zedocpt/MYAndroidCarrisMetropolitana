package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.UrlResponseData

data class UrlUiModel(
    val translationUiModel: List<TranslationUiModel>? = null
)

fun UrlResponseData.toUiModel() = UrlUiModel(
    translationUiModel = translation?.map {
        it.toUiModel()
    }
)