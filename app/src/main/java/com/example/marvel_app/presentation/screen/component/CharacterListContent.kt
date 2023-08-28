package com.example.marvel_app.presentation.screen.component

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.marvel_app.R
import com.example.marvel_app.domain.model.MarvelCharacter
import com.example.marvel_app.presentation.downloadManager.ImageDownloader
import com.example.marvel_app.presentation.screen.likes.CharacterLikesViewModel

@Composable
fun CharacterListContent(
    character: LazyPagingItems<MarvelCharacter>,
    likeCharacter: LazyPagingItems<MarvelCharacter>,
    likesViewModel: CharacterLikesViewModel,
    context: Context
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        items(character.itemCount) { index ->
            character[index]?.let {
                CharacterListItem(
                    character = it,
                    isFavourite = likeCharacter.itemSnapshotList.find { character -> character?.id == it.id } != null,
                    likesViewModel = likesViewModel,
                    context = context
                )
            }
        }
        character.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                loadState.refresh is LoadState.Error -> {
                    val e = character.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    val e = character.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterListItem(character: MarvelCharacter, isFavourite: Boolean, likesViewModel: CharacterLikesViewModel, context: Context) {
    val imageModifier = Modifier
        .size(150.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.White)
    val buttonModifier = Modifier
        .size(25.dp)
        .height(IntrinsicSize.Max)
    val textModifier = Modifier
        .padding(start = 10.dp)
        .width(120.dp)

    val imageUrl = character.thumbnail.path + "." + character.thumbnail.extension
    var isUiFavourite  by rememberSaveable { mutableStateOf(isFavourite) }
    Card(
        modifier = Modifier
            .padding(top = 10.dp)
            .height(180.dp)
            .fillMaxWidth(),

    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth()
                .padding(
                    all = 15.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = imageModifier.clickable( onClick = {
                    val downloader = ImageDownloader(context = context)
                    downloader.downloadImage(imageUrl, character.name)
                }),

            )
            Column(
                modifier = textModifier,
            ) {
                character.name?.let {
                    Text(
                        modifier= Modifier.padding(bottom = 4.dp),
                        style = MaterialTheme.typography.headlineSmall,
                        text = it
                    )
                }
                Text(text = stringResource(R.string.comice_title) + character.comics.available)
                Text(text = stringResource(R.string.event_title) + character.events.available)
                Text(text = stringResource(R.string.stories_title) + character.stories.available)
                Text(text = stringResource(R.string.series_title) + character.series.available)
                Text(text = stringResource(R.string.urls_title) + character.urls.size.toString())
            }
            IconToggleButton(
                modifier = buttonModifier,
                checked = isUiFavourite,
                onCheckedChange = {
                    isUiFavourite = !isUiFavourite
                    if (isUiFavourite){
                        likesViewModel.addFavouriteCharacters(character)
                    }
                    else {
                        likesViewModel.deleteCharacterFromFavourites(character)
                    }
                }) {
                Icon(
                    imageVector = if (isUiFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "like"
                )
            }
        }
    }
}

