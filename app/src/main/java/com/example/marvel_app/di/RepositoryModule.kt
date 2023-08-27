package com.example.marvel_app.di

import com.example.marvel_app.data.db.CharacterDao
import com.example.marvel_app.data.repository.CharacterRepositoryImpl
import com.example.marvel_app.data.repository.dataSource.CharacterRemoteDataSource
import com.example.marvel_app.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideCharacterRepository(
        characterRemoteDataSource: CharacterRemoteDataSource,
        characterLocalDataSource: CharacterDao
    ): CharacterRepository =
        CharacterRepositoryImpl(characterRemoteDataSource, characterLocalDataSource)
}