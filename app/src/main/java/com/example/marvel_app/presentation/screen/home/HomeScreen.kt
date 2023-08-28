package com.farhan.tanvir.androidcleanarchitecture.presentation.screen.home


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.marvel_app.presentation.screen.component.CharacterListContent
import com.example.marvel_app.presentation.screen.home.HomeTopBar
import com.example.marvel_app.presentation.screen.home.HomeViewModel
import com.example.marvel_app.presentation.screen.likes.CharacterLikesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
    likeViewModel: CharacterLikesViewModel = hiltViewModel()
) {

    val allCharacters = viewModel.getAllCharacterUseCase.collectAsLazyPagingItems()
    val likeCharacters = likeViewModel.getLikeCharacters.collectAsLazyPagingItems()
    val context = LocalContext.current

    Scaffold(
        contentColor = MaterialTheme.colorScheme.surface,
        topBar = {
            HomeTopBar(navController)
        }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            CharacterListContent(
                allCharacters, likeCharacters,
                likeViewModel,
                context = context
            )
        }
    }
}
