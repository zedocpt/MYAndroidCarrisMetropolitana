package com.example.carrismetropolitana.screens.lineDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.model.Favorite
import com.example.carrismetropolitana.model.responseData.lines.LineResponseData
import com.example.carrismetropolitana.screens.favorites.FavoriteViewModel
import com.example.carrismetropolitana.screens.lineDetail.viewModel.LineDetailViewModel
import com.example.carrismetropolitana.widgets.CarrisMetroolitanaAppBar

@Composable
fun LineDetailScreen(
    detailAlertViewModel: LineDetailViewModel,
    favoriteViewModel: FavoriteViewModel,
    navController: NavHostController,
    lineId: String? = null
) {
    ShowLineContent(detailAlertViewModel, favoriteViewModel, navController, lineId.orEmpty())
}

@Composable
fun ShowLineContent(
    detailAlertViewModel: LineDetailViewModel,
    favoriteViewModel: FavoriteViewModel,
    navController: NavHostController,
    lineId: String
) {
    val lineData = produceState<DataOrException<LineResponseData, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = detailAlertViewModel.getDetailViewModel(lineId)
    }.value

    if (lineData.loading == true) {
        CircularProgressIndicator(Modifier.fillMaxSize())
    } else {
        Scaffold(
            topBar = {
                CarrisMetroolitanaAppBar(navigationIcon =  true, lineId = lineId, title = "", navController= navController,
                    getFavorite = {
                        if(lineData.data ==  null)  null else{
                            lineData.data?.let {
                                Favorite(id = it.id,
                                    long_name = it.long_name,
                                    short_name = it.short_name,
                                    text_color = it.text_color,
                                    color = it.color)
                            }
                            }
                    })
            },
            bottomBar = { }
        ) { padding ->
            lineData.data?.let {
                Surface(
                    modifier = Modifier
                        .padding(padding)
                        .padding(3.dp)
                        .fillMaxWidth()
                ) {
                    Column {
                        Text(text = it.long_name, color = Color.Black)
                    }
                }
            }
        }
    }
}
