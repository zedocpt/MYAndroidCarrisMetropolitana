package com.example.carrismetropolitana.network


import com.example.carrismetropolitana.model.responseData.alert.AlertsResponseData
import com.example.carrismetropolitana.model.responseData.lines.LineResponseData
import com.example.carrismetropolitana.utils.Constants.ROUTE_ALERTS
import com.example.carrismetropolitana.utils.Constants.ROUTE_ID
import com.example.carrismetropolitana.utils.Constants.ROUTE_ID_LINES
import com.example.carrismetropolitana.utils.Constants.ROUTE_LINES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface CarrisMetropolitanaApi {

    @GET(ROUTE_LINES)
    suspend fun getLinesResponseData() : Response<ArrayList<LineResponseData>>
    @GET(ROUTE_ID_LINES)
    suspend fun getLinesResponseDataById(@Path(value=ROUTE_ID) id : String) : LineResponseData

    @GET(ROUTE_ALERTS)
    suspend fun getAlertsResponseData() : AlertsResponseData
}