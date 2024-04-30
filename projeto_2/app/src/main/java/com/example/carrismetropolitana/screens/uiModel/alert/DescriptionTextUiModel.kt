package com.example.carrismetropolitana.screens.uiModel.alert


import com.example.carrismetropolitana.model.responseData.alert.DescriptionTextResponseData

data class DescriptionTextUiModel(
    val translationUiModel: List<TranslationUiModel>
)

fun DescriptionTextResponseData.toUiModel() = DescriptionTextUiModel(
    translationUiModel = translationResponseData.map {
        it.toUiModel()
    }
)