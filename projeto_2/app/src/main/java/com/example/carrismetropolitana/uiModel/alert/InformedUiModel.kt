package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.InformedEntityResponseData

data class InformedUiModel(
    val routeId: String ? = null,
    val stopId: String ? = null
)

fun InformedEntityResponseData.toUiModel() = InformedUiModel(
    routeId = this.routeId,
    stopId = this.stopId
)