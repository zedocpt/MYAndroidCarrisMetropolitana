package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.AlertResponseData

data class AlertUiModel(
    val activePeriodUiModel: List<ActivePeriodUiModel>,
    val cause: String,
    val descriptionTextUiModel: DescriptionTextUiModel,
    val effect: String,
    val headerTextUiModel: HeaderTextUiModel,
    val imageUiModel: ImageUiModel,
    val informedEntityUiModel: List<InformedUiModel>,
    val urlUiModel: UrlUiModel
)

fun AlertResponseData.toUiModel() = AlertUiModel(
    activePeriodUiModel = activePeriodResponseData.map { it.toUiModel() },
    cause = cause,
    descriptionTextUiModel = descriptionTextResponseData.toUiModel(),
    effect = effect,
    headerTextUiModel = headerTextResponseData.toUiModel(),
    imageUiModel = imageResponseData.toUiModel(),
    informedEntityUiModel = informedEntityResponseData.map {
        it.toUiModel()
    },
    urlUiModel = urlResponseData.toUiModel()
)

