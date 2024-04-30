package com.example.carrismetropolitana.screens.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.InformedEntityResponseData

data class InformedUiModel(
    val routeId: String,
    val stopId: String
)

fun InformedEntityResponseData.toUiModel() = InformedUiModel(
    routeId = this.routeId,
    stopId = this.stopId
)