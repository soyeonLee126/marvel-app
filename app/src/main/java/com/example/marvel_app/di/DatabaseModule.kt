package com.example.marvel_app.di

import android.app.Application
import androidx.room.Room
import com.example.marvel_app.data.db.CharacterDB
import com.example.marvel_app.data.db.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(app: Application): CharacterDB =
        Room.databaseBuilder(app, CharacterDB::class.java, "character_db").fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideCharacterDao(characterDB: CharacterDB) : CharacterDao = characterDB.characterDao()

}