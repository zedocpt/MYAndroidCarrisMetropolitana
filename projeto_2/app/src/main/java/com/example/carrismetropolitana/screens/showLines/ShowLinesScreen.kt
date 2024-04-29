package com.example.carrismetropolitana.screens.showLines

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.model.responseData.lines.LineResponseData
import com.example.carrismetropolitana.model.responseData.lines.LinesResponseData
import com.example.carrismetropolitana.navigation.CarrisMetropolitanaScreens
import com.example.carrismetropolitana.screens.favorites.FavoriteViewModel
import com.example.carrismetropolitana.screens.showLines.entities.LinesWrapperUiModel
import com.example.carrismetropolitana.screens.showLines.entities.ToFavorite
import com.example.carrismetropolitana.utils.hexStringToColor
import com.example.carrismetropolitana.widgets.CarrisMetroolitanaAppBar
import com.example.carrismetropolitana.widgets.CarrisMetroolitanaSearchBar
import com.example.carrismetropolitana.widgets.CarrisMetropolitanaBottomNavigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ShowLinesScreen(
    showLinesViewModel: ShowLinesViewModel,
    favoriteViewModel: FavoriteViewModel,
    navController: NavHostController,
    title: String
) {
    Scaffold(
        topBar = {
            CarrisMetroolitanaAppBar(
                title = title, navController = navController,
                getFavorite = null
            )
        },
        bottomBar = { CarrisMetropolitanaBottomNavigation(navController) }
    ) { paddingValues ->

        val linesData = showLinesViewModel.linesList
        val linesFilterList = showLinesViewModel.linesFilterList.collectAsState().value
        val favoritelist = favoriteViewModel.favList.collectAsState().value
        var linesWrapperListData by remember {
            mutableStateOf<List<LinesWrapperUiModel>>(arrayListOf())
        }

        if (linesData.loading == true) {
            CircularProgressIndicator(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            )
        } else {
            linesData.data?.let { linesResponseData ->
                val lines = arrayListOf<LineResponseData>()
                if (linesFilterList.isEmpty()) {
                    lines.addAll(linesResponseData)
                } else {
                    lines.addAll(linesFilterList)
                }
                LaunchedEffect(lines, favoritelist) {
                    val newLinesWrapperListData = withContext(Dispatchers.Default) {
                        if (favoritelist.isNotEmpty()) {
                            lines.map { line ->
                                val favorite = favoritelist.find { it.id == line.id }
                                LinesWrapperUiModel(line, favorite != null)
                            }
                        } else {
                            lines.map { line ->
                                LinesWrapperUiModel(line, false)
                            }
                        }
                    }
                    linesWrapperListData = newLinesWrapperListData
                }
            }?: run {
                //todo empty state
            }

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                CarrisMetroolitanaSearchBar(hint = "Search",
                    onSearchClicked = {

                    }, onTextChange = {
                        showLinesViewModel.onSearchTextChange(it)
                        Log.d("TAG", "linesWrapperListData")
                    })

                ShowLinesContent(favoriteViewModel, navController, linesWrapperListData)
            }
        }
    }
}

@Composable
fun ShowLinesContent(
    favoriteViewModel: FavoriteViewModel,
    navController: NavController,
    linesWrapperListData: List<LinesWrapperUiModel>
) {
    Surface(modifier = Modifier.padding(2.dp)) {
        LazyColumn(
            modifier = Modifier.padding(2.dp),
            contentPadding = PaddingValues(1.dp)
        ) {
            items(items = linesWrapperListData) { lineWrapperData ->
                LineDetailRow(lineWrapperData,
                    onDetailClick = {
                        navController.navigate(CarrisMetropolitanaScreens.SHOW_LINE.name + "/${lineWrapperData.lineResponseData.id}")
                    },
                    onAddFavoriteClick = {
                        favoriteViewModel.insertFavorite(
                            lineWrapperData.ToFavorite()
                        )
                    }, onRemoveFavoriteClick = {
                        favoriteViewModel.deleteFavorite(lineWrapperData.lineResponseData.id)
                    })
            }
        }
    }
}

@Composable
fun LineDetailRow(
    lineResponseData: LinesWrapperUiModel,
    onDetailClick: () -> Unit,
    onAddFavoriteClick: () -> Unit,
    onRemoveFavoriteClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .clickable { onDetailClick.invoke() },
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(12.dp),
                color = hexStringToColor(lineResponseData.lineResponseData.color)
            ) {
                Text(
                    color = hexStringToColor(lineResponseData.lineResponseData.text_color),
                    text = lineResponseData.lineResponseData.short_name,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = lineResponseData.lineResponseData.long_name,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            if (lineResponseData.isFavorite) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            onRemoveFavoriteClick.invoke()
                        }
                )
            } else {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            onAddFavoriteClick.invoke()
                        }
                )
            }
        }
    }
}

