package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.LocalizedImageResponseData

data class LocalizedImageUiModel(
    val language: String,
    val mediaType: String,
    val url: String
)

fun LocalizedImageResponseData.toUiModel() = LocalizedImageUiModel(
    language = language,
    mediaType = mediaType,
    url = url
)