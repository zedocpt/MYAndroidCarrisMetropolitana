package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.EntityResponseData

data class EntityUiModel(
    val alertUiModel: AlertUiModel? = null,
    val id: String? = null
)

fun EntityResponseData.toUiModel() = EntityUiModel(
    alertUiModel = this.alert?.toUiModel(),
    id = this.id
)