package com.example.carrismetropolitana.model.responseData.alert

data class HeaderResponseData(
    val gtfsRealtimeVersion: String,
    val incrementality: String,
    val timestamp: Int
)