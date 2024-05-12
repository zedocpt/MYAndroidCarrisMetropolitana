package com.example.carrismetropolitana.repository

import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.dataSource.LocalDbDataSource
import com.example.carrismetropolitana.dataSource.NetWorkDataSource
import com.example.carrismetropolitana.model.db.FavoriteDbModel
import com.example.carrismetropolitana.model.responseData.alert.AlertsResponseData
import com.example.carrismetropolitana.model.responseData.lines.LineResponseData
import com.example.carrismetropolitana.model.responseData.wrapper.LinesWrapper
import com.example.carrismetropolitana.retrofit.Result
import kotlinx.coroutines.delay
import javax.inject.Inject

class CarrisMetropolitanaRepository @Inject constructor(
    private val dataSource: NetWorkDataSource,
    private val localDbDataSource: LocalDbDataSource
) {

    suspend fun getLinesMatchWithFavorites(): DataOrException<ArrayList<LinesWrapper>, Boolean, Exception> {
        val linesWrapperList = arrayListOf<LinesWrapper>()
        try {
            val response = dataSource.getLines()
            delay(1000) //TODO just to force seeing loading step
            when (response) {
                is Result.Success -> {
                 //   val lineFavorites = localDbDataSource.getFavorites()
                    val lineFavorites = arrayListOf<FavoriteDbModel>()
                    val linesNetWork = response.body
                    if (!linesNetWork.isNullOrEmpty()) {
                        if (lineFavorites.isNotEmpty()) {
                            linesNetWork.map { line ->
                                val favorite = lineFavorites.find { it.id == line.id }
                                linesWrapperList.add(LinesWrapper(line, favorite != null))
                            }
                        } else {
                            linesNetWork.map { line ->
                                linesWrapperList.add(LinesWrapper(line, false))
                            }
                        }
                    }
                    return DataOrException(linesWrapperList, false, null)
                }

                is Result.Error -> {
                    val code = response.code
                    val message = response.message
                    return DataOrException(null, false, Exception("message:$message- code:$code"))
                }
                else -> {}
            }


        } catch (exception: Exception) {
            return DataOrException(e = exception, loading = false)
        }
        return DataOrException(data = linesWrapperList, loading = false)
    }

    suspend fun getLineId(lineId: String): DataOrException<LineResponseData, Boolean, Exception> {
        return dataSource.getLineId(lineId)
    }

    suspend fun getAlert(): DataOrException<AlertsResponseData, Boolean, Exception> {
        return dataSource.getAlert()
    }
}