package com.example.marvel_app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.marvel_app.data.db.CharacterDao
import com.example.marvel_app.data.paging.CharacterPagingSource
import com.example.marvel_app.data.paging.LikeCharacterPagingSource
import com.example.marvel_app.data.repository.dataSource.CharacterRemoteDataSource
import com.example.marvel_app.domain.model.MarvelCharacter
import com.example.marvel_app.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val characterDao: CharacterDao
) : CharacterRepository {
    override fun getAllCharacters(): Flow<PagingData<MarvelCharacter>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                maxSize = 1000
            ),
            pagingSourceFactory = { CharacterPagingSource(remoteDataSource) }
        ).flow
    }
    override fun getfavouriteCharacterFromDB(): Flow<PagingData<MarvelCharacter>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                maxSize = 100
            ),
            pagingSourceFactory = {  LikeCharacterPagingSource(characterDao) }
        ).flow

    }
    override suspend fun addCharacterToFavourite(marvelCharacter: MarvelCharacter) = characterDao.addCharacter(marvelCharacter)

    override suspend fun deleteCharacterFromFavourite(marvelCharacter: MarvelCharacter) = characterDao.deleteCharacter(marvelCharacter)

}