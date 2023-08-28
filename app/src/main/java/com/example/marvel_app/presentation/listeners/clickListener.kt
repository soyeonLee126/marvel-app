package com.example.marvel_app.presentation.listeners

import com.example.marvel_app.domain.model.MarvelCharacter

interface clickListener {
    fun likeListener(character: MarvelCharacter)
    fun unLikeListener(character: MarvelCharacter)

}