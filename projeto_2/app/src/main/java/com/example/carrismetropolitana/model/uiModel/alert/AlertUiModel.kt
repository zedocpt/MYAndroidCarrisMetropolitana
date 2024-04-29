package com.example.carrismetropolitana.model.uiModel.alert

data class AlertUiModel(
    val activePeriodResponseData: List<ActivePeriodUiModel>,
    val cause: String,
    val descriptionTextResponseData: DescriptionTextUiModel,
    val effect: String,
    val headerTextResponseData: HeaderTextUiModel,
    val imageResponseData: ImageUiModel,
    val informedEntityResponseData: List<InformedUiModel>,
    val urlResponseData: UrlUiModel
)

