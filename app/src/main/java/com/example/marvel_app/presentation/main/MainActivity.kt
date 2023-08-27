package com.example.marvel_app.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.marvel_app.presentation.navigation.NavGraph
import com.example.marvel_app.ui.theme.MarvelappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelappTheme {
                navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

