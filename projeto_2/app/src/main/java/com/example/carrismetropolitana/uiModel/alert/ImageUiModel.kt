package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.ImageResponseData

data class ImageUiModel(
    val localizedImageUiModel: List<LocalizedImageUiModel>? = null
)

fun ImageResponseData.toUiModel() = ImageUiModel(
    localizedImageUiModel = localizedImage?.map {
        it.toUiModel()
    }
)