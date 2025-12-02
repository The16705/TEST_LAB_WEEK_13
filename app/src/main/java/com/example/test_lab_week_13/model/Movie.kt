package com.example.test_lab_week_13.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: Int = 0,

    val adult: Boolean = false,

    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "original_language")
    val originalLanguage: String? = null,

    @Json(name = "original_title")
    val originalTitle: String? = null,

    val overview: String? = null,
    val popularity: Float = 0f,

    @Json(name = "poster_path")
    val posterPath: String? = null,

    @Json(name = "release_date")
    val releaseDate: String? = null,

    val title: String? = null,
    val video: Boolean = false,

    @Json(name = "vote_average")
    val voteAverage: Float = 0f,

    @Json(name = "vote_count")
    val voteCount: Int = 0
)
