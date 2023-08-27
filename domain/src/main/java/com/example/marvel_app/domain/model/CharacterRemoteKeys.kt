package com.example.marvel_app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "character_remote_keys")
data class CharacterRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?,
)