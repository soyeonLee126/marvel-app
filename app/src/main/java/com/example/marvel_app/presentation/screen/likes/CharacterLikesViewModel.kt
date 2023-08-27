package com.example.marvel_app.presentation.screen.likes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvel_app.domain.model.MarvelCharacter
import com.example.marvel_app.domain.usecase.CharacterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterLikesViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCases
) : ViewModel() {
    private val _isFavourite = MutableLiveData(false)
    val isFavourite: MutableLiveData<Boolean> = _isFavourite
    val getLikeCharacters = characterUseCase.getLikeCharacterFromDBUseCase()

    fun deleteCharacterFromFavourites(characterResult: MarvelCharacter) =
        CoroutineScope(Dispatchers.IO).launch {
            characterUseCase.deleteCharacterFromFavourite(characterResult)
        }

    fun addFavouriteCharacters(characterResult: MarvelCharacter) =
        CoroutineScope(Dispatchers.IO).launch {
            characterUseCase.addCharacterToFavouriteUseCase(characterResult)
        }
}