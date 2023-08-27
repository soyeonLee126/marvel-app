package com.example.marvel_app.domain.repository

import androidx.paging.PagingData
import com.example.marvel_app.domain.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllCharacters(): Flow<PagingData<MarvelCharacter>>
    fun getfavouriteCharacterFromDB(): Flow<PagingData<MarvelCharacter>>
    suspend fun addCharacterToFavourite(characterResult: MarvelCharacter)
    suspend fun deleteCharacterFromFavourite(characterResult: MarvelCharacter)
}