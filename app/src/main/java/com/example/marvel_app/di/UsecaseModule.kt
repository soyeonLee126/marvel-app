package com.example.marvel_app.di

import com.example.marvel_app.domain.repository.CharacterRepository
import com.example.marvel_app.domain.usecase.AddCharacterToFavouriteUseCase
import com.example.marvel_app.domain.usecase.CharacterUseCases
import com.example.marvel_app.domain.usecase.DeleteCharacterFromFavouriteUseCase
import com.example.marvel_app.domain.usecase.GetAllCharacterUseCase
import com.example.marvel_app.domain.usecase.GetLikeCharacterUseCaseFromDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideCharacterUseCases(characterRepository: CharacterRepository) = CharacterUseCases(
        getAllCharacterUseCase = GetAllCharacterUseCase(characterRepository = characterRepository),
        getLikeCharacterFromDBUseCase = GetLikeCharacterUseCaseFromDB(characterRepository = characterRepository),
        addCharacterToFavouriteUseCase = AddCharacterToFavouriteUseCase(characterRepository),
        deleteCharacterFromFavourite = DeleteCharacterFromFavouriteUseCase(characterRepository)
    )
}