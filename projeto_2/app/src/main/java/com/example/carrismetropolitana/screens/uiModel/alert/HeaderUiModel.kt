package com.example.carrismetropolitana.screens.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.HeaderResponseData

data class HeaderUiModel(
    val gtfsRealtimeVersion: String,
    val incrementality: String,
    val timestamp: Int
)

fun HeaderResponseData.toUiModel() = HeaderUiModel(
    gtfsRealtimeVersion = gtfsRealtimeVersion,
    incrementality = incrementality,
    timestamp = timestamp
)