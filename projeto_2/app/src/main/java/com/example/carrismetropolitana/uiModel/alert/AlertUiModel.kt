package com.example.carrismetropolitana.uiModel.alert

import com.example.carrismetropolitana.model.responseData.alert.AlertResponseData

data class AlertUiModel(
    val activePeriodUiModel: List<ActivePeriodUiModel>? = null,
    val cause: String? = null,
    val descriptionTextUiModel: DescriptionTextUiModel? = null,
    val effect: String? = null,
    val headerTextUiModel: HeaderTextUiModel? = null,
    val imageUiModel: ImageUiModel ? = null,
    val informedEntityUiModel: List<InformedUiModel>? = null,
    val urlUiModel: UrlUiModel? = null
)

fun AlertResponseData.toUiModel() = AlertUiModel(
    activePeriodUiModel = activePeriod?.map { it.toUiModel() },
    cause = cause,
    descriptionTextUiModel = descriptionText?.toUiModel(),
    effect = effect,
    headerTextUiModel = headerText?.toUiModel(),
    imageUiModel = image?.toUiModel(),
    informedEntityUiModel = informedEntity?.map {
        it.toUiModel()
    },
    urlUiModel = url?.toUiModel()
)

