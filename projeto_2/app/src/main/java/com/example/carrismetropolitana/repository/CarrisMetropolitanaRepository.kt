package com.example.carrismetropolitana.repository

import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.dataSource.LocalDbDataSource
import com.example.carrismetropolitana.dataSource.NeworkDataSource
import com.example.carrismetropolitana.model.responseData.alert.AlertsResponseData
import com.example.carrismetropolitana.model.responseData.lines.LineResponseData
import com.example.carrismetropolitana.model.responseData.wrapper.LinesWrapper
import javax.inject.Inject

class CarrisMetropolitanaRepository @Inject constructor(
    private val dataSource: NeworkDataSource,
    private val localDbDataSource: LocalDbDataSource
) {

    suspend fun getLineMatchWithFavorites(): DataOrException<ArrayList<LinesWrapper>, Boolean, Exception> {
        val linesWrapperList = arrayListOf<LinesWrapper>()
        try {
            val lines = dataSource.getLines()
            val lineFavorites = localDbDataSource.getFavoritesSimple()

            if (lineFavorites.isNotEmpty()) {
                lines.map { line ->
                    val favorite = lineFavorites.find { it.id == line.id }
                    linesWrapperList.add(LinesWrapper(line, favorite != null))
                }
            } else {
                lines.map { line ->
                    linesWrapperList.add(LinesWrapper(line, false))
                }
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