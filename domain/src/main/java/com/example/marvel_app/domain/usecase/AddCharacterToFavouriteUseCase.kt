package com.example.marvel_app.domain.usecase

import com.example.marvel_app.domain.model.MarvelCharacter
import com.example.marvel_app.domain.repository.CharacterRepository

class AddCharacterToFavouriteUseCase(private val characterRepository: CharacterRepository) {
        suspend operator fun invoke(characterResult: MarvelCharacter) = characterRepository.addCharacterToFavourite(characterResult)

}