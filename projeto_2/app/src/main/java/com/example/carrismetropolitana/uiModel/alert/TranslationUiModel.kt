package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.TranslationResponseData

data class TranslationUiModel(
    val language: String? = null,
    val text: String? = null
)
fun TranslationResponseData.toUiModel() = TranslationUiModel(
    language = this.language,
    text = this.text
)