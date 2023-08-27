package com.example.marvel_app.domain.usecase

import com.example.marvel_app.domain.repository.CharacterRepository


class GetLikeCharacterUseCaseFromDB(private val characterRepository: CharacterRepository) {
    operator fun invoke() = characterRepository.getfavouriteCharacterFromDB()
}