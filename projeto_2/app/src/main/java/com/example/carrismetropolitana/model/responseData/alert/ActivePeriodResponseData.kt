package com.example.carrismetropolitana.model.responseData.alert

import com.example.carrismetropolitana.model.uiModel.alert.ActivePeriodUiModel

data class ActivePeriodResponseData(
    val end: Int,
    val start: Int
)
/*
fun ActivePeriodUiModel.toActivePeriodResponseData() = ActivePeriodResponseData(
    end = end,
    start = start,
)*/