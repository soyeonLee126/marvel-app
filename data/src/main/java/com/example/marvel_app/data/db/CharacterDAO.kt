package com.example.marvel_app.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvel_app.domain.model.MarvelCharacter

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(character: MarvelCharacter)
    @Query("SELECT * FROM characters ORDER BY id ASC LIMIT 10 OFFSET (:page-1)*10")
    fun getAllCharacters(page:Int): List<MarvelCharacter>
    @Delete
    suspend fun deleteCharacter(character: MarvelCharacter)

}