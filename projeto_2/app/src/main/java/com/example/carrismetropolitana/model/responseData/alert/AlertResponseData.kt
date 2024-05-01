package com.example.carrismetropolitana.model.responseData.alert

data class AlertResponseData(
    val activePeriod: List<ActivePeriodResponseData> ? = null,
    val cause: String? = null,
    val descriptionText: DescriptionTextResponseData? = null,
    val effect: String? = null,
    val headerText: HeaderTextResponseData ? = null,
    val image: ImageResponseData? = null,
    val informedEntity: List<InformedEntityResponseData> ? = null,
    val url: UrlResponseData? = null
)
