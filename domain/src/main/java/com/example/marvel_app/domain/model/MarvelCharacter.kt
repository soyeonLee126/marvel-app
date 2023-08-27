package com.example.marvel_app.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "characters")
data class MarvelCharacter(
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var description: String = "",
    @Embedded
    var thumbnail: Thumbnail = Thumbnail("", ""),
    @Embedded
    var comics: Comics = Comics(""),
    @Embedded
    var series: Series = Series(""),
    @Embedded
    var stories: Stories = Stories(""),
    @Embedded
    var events: Events = Events(""),
    @Ignore
    var urls: List<Urls> = ArrayList(),
) : Parcelable {
    @Parcelize
    data class Comics(@ColumnInfo(name = "comic_available") val available: String) : Parcelable

    @Parcelize
    data class Series(@ColumnInfo(name = "series_available") val available: String) : Parcelable

    @Parcelize
    data class Stories(@ColumnInfo(name = "stories_available") val available: String) : Parcelable

    @Parcelize
    data class Events(@ColumnInfo(name = "events_available") val available: String) : Parcelable

    @Parcelize
    data class Urls(
        val type: String,
        val url: String
    ): Parcelable

    @Parcelize
    data class Thumbnail(val path: String, val extension: String) : Parcelable
}