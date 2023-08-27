package com.example.marvel_app.domain.usecase

import com.example.marvel_app.domain.repository.CharacterRepository


class GetAllCharacterUseCase(private val characterRepository: CharacterRepository) {
    operator fun invoke() = characterRepository.getAllCharacters()
}