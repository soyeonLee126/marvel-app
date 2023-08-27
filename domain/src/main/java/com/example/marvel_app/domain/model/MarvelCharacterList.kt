package com.example.marvel_app.domain.model

data class MarvelCharacterList(
   val code: Int,
   val status: String,
   val data: Data,
)

data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: ArrayList<MarvelCharacter>
)
