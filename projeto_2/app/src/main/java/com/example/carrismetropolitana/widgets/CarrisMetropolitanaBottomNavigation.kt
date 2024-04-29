package com.example.carrismetropolitana.widgets

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.carrismetropolitana.R
import com.example.carrismetropolitana.navigation.CarrisMetropolitanaScreens

@Composable
fun CarrisMetropolitanaBottomNavigation(navController : NavController, modifier: Modifier = Modifier) {

    val currentDestination = navController.currentDestination?.route
    Log.d("Tag", currentDestination.orEmpty())

    NavigationBar(
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_line)
                )
            },
            selected = currentDestination == CarrisMetropolitanaScreens.SHOW_LINES.name,
            onClick = {
                navController.navigate(CarrisMetropolitanaScreens.SHOW_LINES.name)
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.bottom_navigation_alert))
            },
            selected = currentDestination == CarrisMetropolitanaScreens.Alert.name,
            onClick = {
                navController.navigate(CarrisMetropolitanaScreens.Alert.name)
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.bottom_navigation_favorites))
            },
            selected = currentDestination == CarrisMetropolitanaScreens.FAVORITE.name,
            onClick = {
                navController.navigate(CarrisMetropolitanaScreens.FAVORITE.name)
            }
        )
    }
}