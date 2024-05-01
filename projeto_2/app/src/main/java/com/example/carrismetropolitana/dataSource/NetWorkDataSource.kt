package com.example.carrismetropolitana.dataSource

import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.model.responseData.alert.AlertsResponseData
import com.example.carrismetropolitana.model.responseData.lines.LineResponseData
import com.example.carrismetropolitana.network.CarrisMetropolitanaApi
import javax.inject.Inject

class NetWorkDataSource @Inject constructor(private val api: CarrisMetropolitanaApi) {

    suspend fun getLines() : ArrayList<LineResponseData> {
       return api.getLinesResponseData()
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