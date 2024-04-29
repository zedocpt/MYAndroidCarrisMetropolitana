package com.example.carrismetropolitana.screens.alert

import androidx.lifecycle.ViewModel
import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.model.responseData.alert.AlertsResponseData
import com.example.carrismetropolitana.model.uiModel.alert.AlertsUiModel
import com.example.carrismetropolitana.repository.CarrisMetropolitanaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlertViewModel @Inject constructor(private val repository: CarrisMetropolitanaRepository) : ViewModel() {

    suspend fun getAlerts(): DataOrException<AlertsResponseData, Boolean, Exception> {
        return repository.getAlert()
    }
}