package com.example.carrismetropolitana.navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.carrismetropolitana.screens.alert.AlertScreen
import com.example.carrismetropolitana.screens.alert.AlertViewModel
import com.example.carrismetropolitana.screens.favorites.FavoriteScreen
import com.example.carrismetropolitana.screens.favorites.FavoriteViewModel
import com.example.carrismetropolitana.screens.lineDetail.LineDetailScreen
import com.example.carrismetropolitana.screens.lineDetail.viewModel.LineDetailViewModel
import com.example.carrismetropolitana.screens.main.CarrisMetropolitanaMainScreen
import com.example.carrismetropolitana.screens.showLines.ShowLinesScreen
import com.example.carrismetropolitana.screens.showLines.ShowLinesViewModel
import com.example.carrismetropolitana.screens.splash.CarrisMetropolitanaSplashScreen

@Composable
fun CarrisMetropolitanaNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CarrisMetropolitanaScreens.SplashScreen.name
    ) {

        composable(CarrisMetropolitanaScreens.SplashScreen.name) {
            CarrisMetropolitanaSplashScreen(navController = navController)
        }

        composable(CarrisMetropolitanaScreens.MainScreen.name) {
            CarrisMetropolitanaMainScreen(navController = navController)
        }

        composable(CarrisMetropolitanaScreens.SHOW_LINES.name) {
            val showLinesViewModel = hiltViewModel<ShowLinesViewModel>()
            val favoriteViewModel = hiltViewModel<FavoriteViewModel>()
            ShowLinesScreen(showLinesViewModel,favoriteViewModel, navController = navController, "Lines")
        }

        composable(CarrisMetropolitanaScreens.Alert.name) {
            val alertViewModel = hiltViewModel<AlertViewModel>()
            AlertScreen(alertViewModel, navController = navController, "Alerts")
        }

        val route = CarrisMetropolitanaScreens.SHOW_LINE.name
        composable(
            "$route/{line}",
            arguments = listOf(
                navArgument(
                    name = "line"
                ) {
                    type = NavType.StringType
                })
        ) { navBack ->
            navBack.arguments?.getString("line").let { lineId ->
                val detailAlertViewModel = hiltViewModel<LineDetailViewModel>()
                LineDetailScreen(detailAlertViewModel,  navController, lineId)
            }
        }

        composable(CarrisMetropolitanaScreens.FAVORITE.name) {
            val favoriteViewModel = hiltViewModel<FavoriteViewModel>()
            FavoriteScreen(favoriteViewModel,  navController, "Favorite")
        }
    }
}