package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.TranslationResponseData

data class TranslationUiModel(
    val language: String,
    val text: String
)
fun TranslationResponseData.toUiModel() = TranslationUiModel(
    language = this.language,
    text = this.text
)