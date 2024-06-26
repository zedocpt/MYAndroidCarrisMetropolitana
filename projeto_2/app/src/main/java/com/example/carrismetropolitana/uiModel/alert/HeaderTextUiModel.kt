package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.HeaderTextResponseData

data class HeaderTextUiModel(
    val translationUiModel: List<TranslationUiModel>? = null
)

fun HeaderTextResponseData.toUiModel() = HeaderTextUiModel(
    translationUiModel = translation?.map { it.toUiModel() },
)