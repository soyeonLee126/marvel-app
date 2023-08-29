package com.example.marvel_app.presentation.main

import androidx.lifecycle.ViewModel
import com.example.marvel_app.domain.usecase.CharacterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor( private val characterUseCase: CharacterUseCases
) : ViewModel() {

}
