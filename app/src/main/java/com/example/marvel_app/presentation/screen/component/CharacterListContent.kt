package com.example.marvel_app.presentation.screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.marvel_app.R
import com.example.marvel_app.domain.model.MarvelCharacter
import com.example.marvel_app.presentation.screen.likes.CharacterLikesViewModel

@Composable
fun CharacterListContent(
    character: LazyPagingItems<MarvelCharacter>,
    viewModel: CharacterLikesViewModel
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        items(character.itemCount) { index ->
            character[index]?.let {
                CharacterListItem(
                    character = it,
                    viewModel = viewModel
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
fun CharacterListItem(character: MarvelCharacter, viewModel: CharacterLikesViewModel) {
    val imageModifier = Modifier
        .size(150.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.White)
    val buttonModifier = Modifier
        .size(25.dp)
        .height(IntrinsicSize.Max)
    val textModifier = Modifier
        .padding(start = 10.dp)

    val imageUrl = character.thumbnail.path + "." + character.thumbnail.extension
    val isFavourite =
        (viewModel.getLikeCharacters.collectAsLazyPagingItems().itemSnapshotList.find { it!!.id == character.id } != null)

    Card(
        modifier = Modifier
            .padding(top = 10.dp)
            .height(180.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                Modifier
                    .height(IntrinsicSize.Max)
                    .padding(
                        all = 15.dp
                    )
            ) {
                ConstraintLayout {
                    val (AsyncImage, iconToggleButton) = createRefs()
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                    Column(
                        modifier = textModifier
                    ) {
                        character.name?.let {
                            Text(text = it)
                        }
                        Text(text = stringResource(R.string.comice_title) + character.comics.available)
                        Text(text = stringResource(R.string.event_title) + character.events.available)
                        Text(text = stringResource(R.string.stories_title) + character.stories.available)
                        Text(text = stringResource(R.string.series_title) + character.series.available)
                    }
                    IconToggleButton(
                        modifier = Modifier.constrainAs(iconToggleButton) {
                            end.linkTo(parent.end)
                        },
                        checked = isFavourite,
                        onCheckedChange = {
                            if (!isFavourite) viewModel.addFavouriteCharacters(character)
                            else {
                                viewModel.deleteCharacterFromFavourites(character)
                            }
                        }) {
                        Icon(
                            imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "like"
                        )
                    }
                }
            }
        }
    }
}
