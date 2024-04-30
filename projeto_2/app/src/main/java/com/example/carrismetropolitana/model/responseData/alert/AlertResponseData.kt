package com.example.carrismetropolitana.model.responseData.alert

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
