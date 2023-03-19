package com.example.flixsterplus

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class SearchNewsResponse(
    @SerialName("results")
    val result: List<PopularMovie>?
)


@Keep
@Serializable
data class PopularMovie (
    @SerialName("poster_path")
    val poster: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("popularity")
    var voteAvg: Double? = null,
    @SerialName("adult")
    var adult: Boolean? = null
) : java.io.Serializable