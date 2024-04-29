package com.example.carrismetropolitana.repository

import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.model.responseData.alert.AlertResponseData
import com.example.carrismetropolitana.model.responseData.alert.AlertsResponseData
import com.example.carrismetropolitana.model.responseData.lines.LineResponseData
import com.example.carrismetropolitana.model.uiModel.alert.AlertsUiModel
import com.example.carrismetropolitana.model.responseData.lines.LineUiModel
import com.example.carrismetropolitana.model.responseData.lines.LinesResponseData
import com.example.carrismetropolitana.model.responseData.lines.LinesUiModel
import com.example.carrismetropolitana.network.CarrisMetropolitanaApi
import javax.inject.Inject

class CarrisMetropolitanaRepository @Inject constructor(private val api: CarrisMetropolitanaApi){

    suspend fun getLines() : DataOrException<LinesResponseData,Boolean, Exception > {
        val responseData = try{ api.getLinesResponseData()
        }catch (exception: Exception){
            return DataOrException(e = exception, loading = false)
        }
        return DataOrException(data = responseData, loading = false)
    }

    suspend fun getLineId(lineId : String) : DataOrException<LineResponseData,Boolean, Exception > {
        val responseData = try{
            api.getLinesResponseDataById(lineId)
        }catch (exception: Exception){
            return DataOrException(e = exception)
        }
        return DataOrException(data = responseData)
    }

    suspend fun getAlert() : DataOrException<AlertsResponseData,Boolean, Exception > {
        val responseData = try{
            api.getAlertsResponseData()
        }catch (exception: Exception){
            return DataOrException(e = exception)
        }
        return DataOrException(data = responseData)
    }
}