package kz.android.tron.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val backdropPath: String,
    val genreId: List<Int>,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Int,
    val voteCount: Int,
    var bigPosterPath: String
) : Parcelable