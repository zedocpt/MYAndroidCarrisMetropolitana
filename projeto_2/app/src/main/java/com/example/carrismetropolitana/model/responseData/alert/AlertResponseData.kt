package com.example.carrismetropolitana.model.responseData.alert

import com.example.carrismetropolitana.model.uiModel.alert.AlertUiModel

data class AlertResponseData(
    val activePeriodResponseData: List<ActivePeriodResponseData>,
    val cause: String,
    val descriptionTextResponseData: DescriptionTextResponseData,
    val effect: String,
    val headerTextResponseData: HeaderTextResponseData,
    val imageResponseData: ImageResponseData,
    val informedEntityResponseData: List<InformedEntityResponseData>,
    val urlResponseData: UrlResponseData
)

//fun AlertUiModel.toAlertResponseData() = AlertResponseData(
//    activePeriodResponseData = this.activePeriodResponseData,
//    cause = this.cause,
//    descriptionTextResponseData = this.descriptionTextResponseData,
//    effect = this.effect,
//    headerTextResponseData = this.headerTextResponseData,
//    imageResponseData = this.imageResponseData,
//    informedEntityResponseData = this.informedEntityResponseData,
//    urlResponseData = this.urlResponseData
//)