package com.example.marvel_app.data.repository.dataSourceImpl

import com.example.marvel_app.data.api.CharacterApi
import com.example.marvel_app.data.repository.dataSource.CharacterRemoteDataSource
import com.example.marvel_app.domain.model.MarvelCharacterList
import retrofit2.Response
import javax.inject.Inject


class CharacterRemoteDataSourceImpl @Inject constructor(private val api: CharacterApi) : CharacterRemoteDataSource {
    override suspend fun getAllCharacters(
        offset: Int,
        ts: String,
        apiKey: String,
        hash: String
    ): Response<MarvelCharacterList> {
       return api.getAllCharacters(offset, ts, apiKey, hash)
    }

}