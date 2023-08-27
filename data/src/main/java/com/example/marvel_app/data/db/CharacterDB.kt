package com.example.marvel_app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvel_app.domain.model.MarvelCharacter

@Database(
    entities = [MarvelCharacter::class],
    version = 1,
)
abstract class CharacterDB : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
