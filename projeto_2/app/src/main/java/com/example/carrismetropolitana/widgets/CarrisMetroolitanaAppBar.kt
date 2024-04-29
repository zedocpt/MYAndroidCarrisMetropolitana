package com.example.carrismetropolitana.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.carrismetropolitana.model.Favorite
import com.example.carrismetropolitana.screens.favorites.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarrisMetroolitanaAppBar(
    navigationIcon: Boolean? = null,
    title: String = "",
    navController: NavController,
    lineId: String? = null,
    getFavorite: (() -> Favorite?)?,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
) {

    TopAppBar(
        title = {
            Row(
            ) {
                Text(title)
            }
        },
        navigationIcon = {
            if (navigationIcon == true) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "ArrowBack"
                    )
                }
            }
        },
        actions = {
            val currentDestination = navController.currentDestination?.route
            if (currentDestination == "SHOW_LINE/{line}") {
                val favoritelist = favoriteViewModel.favList.collectAsState().value
                val isAlreadyFavList = favoritelist.filter { item -> (item.id == lineId) }
                if (isAlreadyFavList.isEmpty()) {
                    Icon(
                        modifier = Modifier.clickable {
                            getFavorite?.let {
                                it.invoke()?.let { favorite ->
                                    favoriteViewModel.insertFavorite(
                                        favorite
                                    )
                                }
                            }
                        },
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                    )
                } else {
                    Icon(
                        modifier = Modifier.clickable {
                            favoriteViewModel.deleteFavorite(
                                lineId.orEmpty()
                            )
                        },
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Favorite",
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer),
    )
}