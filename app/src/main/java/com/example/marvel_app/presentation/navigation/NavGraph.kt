package com.example.marvel_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marvel_app.presentation.screen.likes.CharacterLikesScreen
import com.farhan.tanvir.androidcleanarchitecture.presentation.screen.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.CharacterLikes.route,
        ) { CharacterLikesScreen(navController) }
    }
}