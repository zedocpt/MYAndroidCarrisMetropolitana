package com.example.carrismetropolitana.screens.alert

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.model.responseData.alert.AlertsResponseData
import com.example.carrismetropolitana.model.responseData.alert.EntityResponseData
import com.example.carrismetropolitana.widgets.CarrisMetroolitanaAppBar
import com.example.carrismetropolitana.widgets.CarrisMetropolitanaBottomNavigation

@Composable
fun AlertScreen(alertViewModel: AlertViewModel, navController: NavController, title: String) {
    Scaffold(
        topBar = {
            CarrisMetroolitanaAppBar(false, title, navController, getFavoriteDbModel = null)
        },
        bottomBar = { CarrisMetropolitanaBottomNavigation(navController) }
    ) { padding ->
        ShowAlertsContent(alertViewModel, padding)
    }
}

@Composable
fun ShowAlertsContent(alertViewModel: AlertViewModel, paddingValues: PaddingValues) {
    val alertsData = produceState<DataOrException<AlertsResponseData, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = alertViewModel.getAlerts()
    }.value

    if (alertsData.loading == true) {
        CircularProgressIndicator(Modifier.fillMaxSize())
    } else {
        Surface(modifier = Modifier.padding(2.dp)) {
            alertsData.data?.let { alertsData ->
                LazyColumn(
                    modifier = Modifier.padding(2.dp),
                    contentPadding = PaddingValues(1.dp)
                ) {
                    alertsData.entityResponseData?.let {
                        items(items = alertsData.entityResponseData) { entity ->
                            AlertDetailRow(entity)

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AlertDetailRow(entityResponseData: EntityResponseData) {
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {

        }
    }
}

