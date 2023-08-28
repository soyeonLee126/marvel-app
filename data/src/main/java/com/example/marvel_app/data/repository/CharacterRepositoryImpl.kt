package com.example.marvel_app.data.repository

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.net.toUri
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.marvel_app.data.db.CharacterDao
import com.example.marvel_app.data.paging.CharacterPagingSource
import com.example.marvel_app.data.paging.LikeCharacterPagingSource
import com.example.marvel_app.data.repository.dataSource.CharacterRemoteDataSource
import com.example.marvel_app.domain.model.MarvelCharacter
import com.example.marvel_app.domain.repository.CharacterRepository
import com.example.marvel_app.util.Constants.LOAD_SIZE
import kotlinx.coroutines.flow.Flow
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val characterDao: CharacterDao
) : CharacterRepository {
    override fun getAllCharacters(): Flow<PagingData<MarvelCharacter>> {
        return Pager(
            config = PagingConfig(
                pageSize = LOAD_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { CharacterPagingSource(remoteDataSource) }
        ).flow
    }
    override fun getfavouriteCharacterFromDB(): Flow<PagingData<MarvelCharacter>> {
        return Pager(
            config = PagingConfig(
                pageSize = LOAD_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {  LikeCharacterPagingSource(characterDao) }
        ).flow

    }
    override suspend fun addCharacterToFavourite(marvelCharacter: MarvelCharacter) = characterDao.addCharacter(marvelCharacter)

    override suspend fun deleteCharacterFromFavourite(marvelCharacter: MarvelCharacter) = characterDao.deleteCharacter(marvelCharacter)

}