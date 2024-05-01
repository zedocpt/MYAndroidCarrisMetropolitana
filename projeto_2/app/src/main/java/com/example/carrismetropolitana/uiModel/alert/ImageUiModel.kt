package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.ImageResponseData

data class ImageUiModel(
    val localizedImageUiModel: List<LocalizedImageUiModel>
)

fun ImageResponseData.toUiModel() = ImageUiModel(
    localizedImageUiModel = localizedImageResponseData.map {
        it.toUiModel()
    }
)