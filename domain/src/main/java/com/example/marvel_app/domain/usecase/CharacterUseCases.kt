package com.example.marvel_app.domain.usecase



data class CharacterUseCases(
    val getAllCharacterUseCase: GetAllCharacterUseCase,
    val getLikeCharacterFromDBUseCase: GetLikeCharacterUseCaseFromDB,
    val addCharacterToFavouriteUseCase: AddCharacterToFavouriteUseCase,
    val deleteCharacterFromFavourite: DeleteCharacterFromFavouriteUseCase,
)