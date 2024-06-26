package com.example.carrismetropolitana.screens.lineDetail.viewModel

import androidx.lifecycle.ViewModel
import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.dataSource.NetWorkDataSource
import com.example.carrismetropolitana.model.responseData.lines.LineResponseData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LineDetailViewModel @Inject constructor(private val netWorkDataSource: NetWorkDataSource) : ViewModel(){
    suspend fun getDetailViewModel(lineId: String): DataOrException<LineResponseData, Boolean, Exception> {
        return netWorkDataSource.getLineId(lineId)
    }
}