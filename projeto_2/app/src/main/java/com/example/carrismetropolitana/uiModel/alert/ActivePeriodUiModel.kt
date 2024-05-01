package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.ActivePeriodResponseData

data class ActivePeriodUiModel(
    val end: Int? = null,
    val start: Int? = null
)

fun ActivePeriodResponseData.toUiModel() = ActivePeriodUiModel(
    end = this.end,
    start = this.start
)