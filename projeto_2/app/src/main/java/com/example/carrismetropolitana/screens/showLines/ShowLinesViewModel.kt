package com.example.carrismetropolitana.screens.showLines

import androidx.lifecycle.ViewModel
import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.model.responseData.lines.LinesResponseData
import com.example.carrismetropolitana.model.responseData.lines.LinesUiModel
import com.example.carrismetropolitana.repository.CarrisMetropolitanaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowLinesViewModel @Inject constructor(private val repository: CarrisMetropolitanaRepository) :
    ViewModel() {

    suspend fun getLines(): DataOrException<LinesResponseData, Boolean, Exception> {
        return repository.getLines()
    }
}