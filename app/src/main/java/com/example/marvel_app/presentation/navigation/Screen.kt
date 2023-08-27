package com.example.marvel_app.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object CharacterLikes : Screen("character_likes_screen")
}