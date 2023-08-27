package com.example.marvel_app.data.repository.dataSource

import com.example.marvel_app.domain.model.MarvelCharacterList
import retrofit2.Response

interface CharacterRemoteDataSource {
    suspend fun getAllCharacters(offset: Int, ts: String, apiKey: String, hash: String): Response<MarvelCharacterList>
}