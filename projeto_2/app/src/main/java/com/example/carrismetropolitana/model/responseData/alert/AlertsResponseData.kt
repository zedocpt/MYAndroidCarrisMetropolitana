package com.example.carrismetropolitana.model.responseData.alert

import com.example.carrismetropolitana.model.uiModel.alert.EntityUiModel
import com.example.carrismetropolitana.model.uiModel.alert.HeaderUiModel

data class AlertsResponseData(
    val entityResponseData: List<EntityResponseData>,
    val headerResponseData: HeaderResponseData
)