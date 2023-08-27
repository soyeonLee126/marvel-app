package com.example.marvel_app.di

import com.example.marvel_app.data.api.CharacterApi
import com.example.marvel_app.data.repository.dataSource.CharacterRemoteDataSource
import com.example.marvel_app.data.repository.dataSourceImpl.CharacterRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    @Singleton
    fun provideCharacterRemoteDataSource(characterApi: CharacterApi) : CharacterRemoteDataSource = CharacterRemoteDataSourceImpl(characterApi)
}