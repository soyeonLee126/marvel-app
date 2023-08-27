package com.example.marvel_app.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.example.marvel_app.domain.usecase.CharacterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    characterUseCases: CharacterUseCases,
) : ViewModel() {
    val getAllCharacterUseCase = characterUseCases.getAllCharacterUseCase()
}