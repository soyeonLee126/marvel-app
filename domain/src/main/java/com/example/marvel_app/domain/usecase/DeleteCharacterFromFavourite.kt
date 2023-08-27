package com.example.marvel_app.domain.usecase

import com.example.marvel_app.domain.model.MarvelCharacter
import com.example.marvel_app.domain.repository.CharacterRepository

class DeleteCharacterFromFavouriteUseCase(private val characterRepository: CharacterRepository) {
        suspend operator fun invoke(characterResult: MarvelCharacter) = characterRepository.deleteCharacterFromFavourite(characterResult)

}