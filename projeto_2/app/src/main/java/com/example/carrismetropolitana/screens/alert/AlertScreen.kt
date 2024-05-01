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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.model.responseData.alert.AlertsResponseData
import com.example.carrismetropolitana.model.responseData.alert.EntityResponseData
import com.example.carrismetropolitana.uiModel.alert.EntityUiModel
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
    val alerts = alertViewModel.alertsList
    if (alerts.loading == true) {
        CircularProgressIndicator(Modifier.fillMaxSize())
    } else {
        if (alerts.e == null) {
            Surface(modifier = Modifier.padding(paddingValues)) {
                alerts.data?.let { alertsData ->
                    if (!alertsData.entityUiModel.isNullOrEmpty()) {
                        LazyColumn(
                            modifier = Modifier.padding(2.dp),
                            contentPadding = PaddingValues(1.dp)
                        ) {
                            items(items =alertsData.entityUiModel) { entityUiModel ->
                                AlertDetailRow(entityUiModel)

                            }
                        }
                    }
                } ?: run {
                    //todo show empty state
                }
            }
        } else {
            //todo show error
        }
    }
}

@Composable
fun AlertDetailRow(entityUiModel: EntityUiModel) {
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
            Text(text = entityUiModel.alertUiModel?.cause.orEmpty())
        }
    }
}

