package com.example.carrismetropolitana.screens.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carrismetropolitana.uiModel.favorite.FavoriteUiModel
import com.example.carrismetropolitana.navigation.CarrisMetropolitanaScreens
import com.example.carrismetropolitana.utils.hexStringToColor
import com.example.carrismetropolitana.widgets.CarrisMetroolitanaAppBar
import com.example.carrismetropolitana.widgets.CarrisMetropolitanaBottomNavigation

@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoriteViewModel,
    navController: NavHostController,
    title: String
) {

    Scaffold(
        topBar = {
            CarrisMetroolitanaAppBar(
                title = title,
                navigationIcon = false,
                navController = navController,
                getFavoriteDbModel = null
            )
        },
        bottomBar = { CarrisMetropolitanaBottomNavigation(navController) }
    ) { padding ->
        ShowFavoriteContent(favoriteViewModel, navController, padding)
    }
}

@Composable
fun ShowFavoriteContent(
    favoriteViewModel: FavoriteViewModel,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val favList = favoriteViewModel.favList.collectAsState().value

    Surface(modifier = Modifier.padding(2.dp)) {
        if (favList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(items = favList) { favItem ->
                    FavoriteRow(favItem,
                        onClick = {
                            navController.navigate(CarrisMetropolitanaScreens.SHOW_LINE.name + "/${favItem.id}")
                        },
                        onDeleteClick = {
                            favoriteViewModel.deleteFavorite(favItem.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FavoriteRow(
    favoriteUiModel: FavoriteUiModel,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {

    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .clickable { onClick() },
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
                color = hexStringToColor(favoriteUiModel.color)
            ) {
                Text(
                    color = hexStringToColor(favoriteUiModel.text_color),
                    text = favoriteUiModel.short_name,
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
                    text = favoriteUiModel.long_name,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onDeleteClick() }
            )
        }
    }
}
