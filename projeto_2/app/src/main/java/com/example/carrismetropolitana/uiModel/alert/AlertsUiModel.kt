package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.AlertsResponseData

data class AlertsUiModel(
    val entityUiModel: List<EntityUiModel>? = null,
    val headerUiModel: HeaderUiModel? = null
)

fun AlertsResponseData.toUiModel() = AlertsUiModel(
    entityUiModel = this.entity?.map {
        it.toUiModel()
    },
    headerUiModel = this.header?.toUiModel()
)