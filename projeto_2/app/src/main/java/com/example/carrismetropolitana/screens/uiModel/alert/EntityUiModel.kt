package com.example.carrismetropolitana.screens.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.EntityResponseData

data class EntityUiModel(
    val alertUiModel: AlertUiModel,
    val id: String
)

fun EntityResponseData.toUiModel() = EntityUiModel(
    alertUiModel = this.alertResponseData.toUiModel(),
    id = this.id
)