package com.example.carrismetropolitana.screens.alert

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.model.responseData.alert.AlertsResponseData
import com.example.carrismetropolitana.uiModel.alert.AlertsUiModel
import com.example.carrismetropolitana.repository.CarrisMetropolitanaRepository
import com.example.carrismetropolitana.uiModel.alert.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlertViewModel @Inject constructor(private val repository: CarrisMetropolitanaRepository) :
    ViewModel() {

    private val _alertsList: MutableState<DataOrException<AlertsUiModel, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, null))

    val alertsList: DataOrException<AlertsUiModel, Boolean, Exception>
        get() = _alertsList.value

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAlert().let {
                val data = it.data
                data?.let { alert ->
                    _alertsList.value.data = alert.toUiModel()
                }
                _alertsList.value.e = it.e
                _alertsList.value.loading = it.loading
            }
        }
    }


}