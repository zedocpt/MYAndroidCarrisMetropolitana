package com.example.carrismetropolitana.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.carrismetropolitana.widgets.CarrisMetropolitanaBottomNavigation

@Composable
fun CarrisMetropolitanaMainScreen(navController: NavController){
    MainScaffold(navController)
}

@Composable
fun MainScaffold(navController : NavController){
    Scaffold(
        bottomBar = { CarrisMetropolitanaBottomNavigation(navController) }
    ) { padding ->
        Box(Modifier.padding(padding))
    }
}

