package com.example.carrismetropolitana.uiModel.alert


import com.example.carrismetropolitana.model.responseData.alert.DescriptionTextResponseData

data class DescriptionTextUiModel(
    val translationUiModel: List<TranslationUiModel>? = null
)

fun DescriptionTextResponseData.toUiModel() = DescriptionTextUiModel(
    translationUiModel = translation?.map {
        it.toUiModel()
    }
)