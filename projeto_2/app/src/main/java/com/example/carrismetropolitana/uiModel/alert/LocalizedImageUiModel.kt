package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.LocalizedImageResponseData

data class LocalizedImageUiModel(
    val language: String? = null,
    val mediaType: String? = null,
    val url: String? = null
)

fun LocalizedImageResponseData.toUiModel() = LocalizedImageUiModel(
    language = language,
    mediaType = mediaType,
    url = url
)