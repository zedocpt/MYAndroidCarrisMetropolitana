package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.HeaderResponseData

data class HeaderUiModel(
    val gtfsRealtimeVersion: String? = null,
    val incrementality: String? = null,
    val timestamp: Int? = null
)

fun HeaderResponseData.toUiModel() = HeaderUiModel(
    gtfsRealtimeVersion = gtfsRealtimeVersion,
    incrementality = incrementality,
    timestamp = timestamp
)