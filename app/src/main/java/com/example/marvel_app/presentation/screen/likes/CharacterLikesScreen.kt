package com.example.marvel_app.presentation.screen.likes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.marvel_app.presentation.screen.component.CharacterListContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterLikesScreen(
    navController: NavController,
    viewModel: CharacterLikesViewModel = hiltViewModel(),
) {
    val allCharacters = viewModel.getLikeCharacters.collectAsLazyPagingItems()

    Scaffold(
        contentColor = MaterialTheme.colorScheme.surface,
        topBar = {
            CharacterLikesTopBar(navController)
        }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            CharacterListContent(allCharacters, viewModel)
        }
    }
}