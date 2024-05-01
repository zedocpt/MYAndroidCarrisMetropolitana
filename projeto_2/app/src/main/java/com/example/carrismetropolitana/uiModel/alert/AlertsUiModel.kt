package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.AlertsResponseData

data class AlertsUiModel(
    val entityUiModel: List<EntityUiModel>,
    val headerUiModel: HeaderUiModel
)

fun AlertsResponseData.toUiModel() = AlertsUiModel(
    entityUiModel = this.entityResponseData.map {
        it.toUiModel()
    },
    headerUiModel = this.headerResponseData.toUiModel()
)