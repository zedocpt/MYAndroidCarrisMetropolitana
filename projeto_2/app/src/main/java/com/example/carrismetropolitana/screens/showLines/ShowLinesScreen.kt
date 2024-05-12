package com.example.carrismetropolitana.screens.showLines

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.carrismetropolitana.navigation.CarrisMetropolitanaScreens
import com.example.carrismetropolitana.screens.composables.Loader
import com.example.carrismetropolitana.screens.favorites.FavoriteViewModel
import com.example.carrismetropolitana.uiModel.favorite.LinesWrapperUiModel
import com.example.carrismetropolitana.uiModel.favorite.ToFavorite
import com.example.carrismetropolitana.utils.hexStringToColor
import com.example.carrismetropolitana.widgets.CarrisMetroolitanaAppBar
import com.example.carrismetropolitana.widgets.CarrisMetroolitanaSearchBar
import com.example.carrismetropolitana.widgets.CarrisMetropolitanaBottomNavigation

@Composable
fun ShowLinesScreen(
    modifier: Modifier,
    showLinesViewModel: ShowLinesViewModel,
    favoriteViewModel: FavoriteViewModel,
    navController: NavHostController,
    title: String
) {
    Scaffold(
        topBar = {
            CarrisMetroolitanaAppBar(
                title = title, navController = navController,
                getFavoriteDbModel = null
            )
        },
        bottomBar = { CarrisMetropolitanaBottomNavigation(navController) }
    ) {  paddingValues ->
        val linesData = showLinesViewModel.linesList
        if (linesData.loading == true) {
            Loader(modifier.fillMaxSize())
        } else {
            if (linesData.e == null) {
                if (showLinesViewModel.linesFilterList.value.isNotEmpty()) {
                    populateDataLines(
                        showLinesViewModel.linesFilterList.value,
                        paddingValues,
                        navController,
                        showLinesViewModel,
                        favoriteViewModel
                    )
                } else {
                    linesData.data?.let { list ->
                        populateDataLines(
                            list,
                            paddingValues,
                            navController,
                            showLinesViewModel,
                            favoriteViewModel
                        )
                    } ?: run {
                        //todo show empty state
                    }
                }
            }else {
                //todo show error
            }
        }
    }
}

@Composable
fun populateDataLines(
    list: List<LinesWrapperUiModel>,
    paddingValues: PaddingValues,
    navController: NavHostController,
    showLinesViewModel: ShowLinesViewModel,
    favoriteViewModel: FavoriteViewModel
) {
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

        ShowLinesContent(favoriteViewModel, navController, list)
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
                        navController.navigate(CarrisMetropolitanaScreens.SHOW_LINE.name + "/${lineWrapperData.lineUiModel.id}")
                    },
                    onAddFavoriteClick = {
                        favoriteViewModel.insertFavorite(
                            lineWrapperData.ToFavorite()
                        )
                    }, onRemoveFavoriteClick = {
                        favoriteViewModel.deleteFavorite(lineWrapperData.lineUiModel.id)
                    })
            }
        }
    }
}

@Composable
fun LineDetailRow(
    linesWrapperUiModel: LinesWrapperUiModel,
    onDetailClick: () -> Unit,
    onAddFavoriteClick: () -> Unit,
    onRemoveFavoriteClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .background(Color.White, shape = CircleShape.copy(topEnd = CornerSize(6.dp)))
            .clickable { onDetailClick.invoke() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(12.dp),
            color = hexStringToColor(linesWrapperUiModel.lineUiModel.color)
        ) {
            Text(
                color = hexStringToColor(linesWrapperUiModel.lineUiModel.text_color),
                text = linesWrapperUiModel.lineUiModel.short_name,
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
                text = linesWrapperUiModel.lineUiModel.long_name,
                style = MaterialTheme.typography.bodySmall
            )
        }

        if (linesWrapperUiModel.isFavorite) {
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
                        onAddFavoriteClick()
                    }
            )
        }
    }
}

