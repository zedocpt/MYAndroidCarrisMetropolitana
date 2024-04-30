package com.example.carrismetropolitana.repository

import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.dataSource.LocalDbDataSource
import com.example.carrismetropolitana.dataSource.NeworkDataSource
import com.example.carrismetropolitana.model.responseData.alert.AlertsResponseData
import com.example.carrismetropolitana.model.responseData.lines.LineResponseData
import com.example.carrismetropolitana.screens.showLines.entities.LinesWrapperUiModel
import javax.inject.Inject

class CarrisMetropolitanaRepository @Inject constructor(
    private val dataSource: NeworkDataSource,
    private val localDbDataSource: LocalDbDataSource
) {

    suspend fun GetLineMatchWithFavorites(): DataOrException<ArrayList<LinesWrapperUiModel>, Boolean, Exception> {
        val linesWrapperUiModelList = arrayListOf<LinesWrapperUiModel>()
        try {
            val lines = dataSource.getLines()
            val lineFavorites = localDbDataSource.getFavoritesSimple()

            if (lineFavorites.isNotEmpty()) {   //todo improvements mapping
                lines.map { line ->
                    val favorite = lineFavorites.find { it.id == line.id }
                    linesWrapperUiModelList.add(LinesWrapperUiModel(line, favorite != null))
                }
            } else {
                lines.map { line ->
                    linesWrapperUiModelList.add(LinesWrapperUiModel(line, false))
                }
            }

        } catch (exception: Exception) {
            return DataOrException(e = exception, loading = false)
        }
        return DataOrException(data = linesWrapperUiModelList, loading = false)
    }

    suspend fun getLineId(lineId: String): DataOrException<LineResponseData, Boolean, Exception> {
        return dataSource.getLineId(lineId)
    }

    suspend fun getAlert(): DataOrException<AlertsResponseData, Boolean, Exception> {
        return dataSource.getAlert()
    }
}