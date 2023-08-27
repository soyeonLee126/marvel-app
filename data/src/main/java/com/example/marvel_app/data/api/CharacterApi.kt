package com.example.marvel_app.data.api

import com.example.marvel_app.domain.model.MarvelCharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {
    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query(
           "offset"
        ) offset: Int,
        @Query(
            "ts"
        ) ts:String,
        @Query(
            "apikey"
        ) apiKey: String,
        @Query(
            "hash"
        ) hash: String,
    ): Response<MarvelCharacterList>
}