package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.ActivePeriodResponseData

data class ActivePeriodUiModel(
    val end: Int,
    val start: Int
)

fun ActivePeriodResponseData.toUiModel() = ActivePeriodUiModel(
    end = this.end,
    start = this.start
)