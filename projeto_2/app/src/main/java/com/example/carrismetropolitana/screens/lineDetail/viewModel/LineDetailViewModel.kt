package com.example.carrismetropolitana.screens.lineDetail.viewModel

import androidx.lifecycle.ViewModel
import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.model.responseData.lines.LineResponseData
import com.example.carrismetropolitana.model.responseData.lines.LineUiModel
import com.example.carrismetropolitana.repository.CarrisMetropolitanaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LineDetailViewModel @Inject constructor(private val repository: CarrisMetropolitanaRepository) : ViewModel(){
    suspend fun getDetailViewModel(lineId: String): DataOrException<LineResponseData, Boolean, Exception> {
        return repository.getLineId(lineId)
    }
}