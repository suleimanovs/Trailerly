package com.android.tron.data.api

import com.android.tron.data.api.pojo.MovieRemote
import com.android.tron.data.api.pojo.ReviewRemote
import com.android.tron.data.api.pojo.TrailerRemote
import com.android.tron.domain.pojo.Movie
import com.android.tron.domain.pojo.Review
import com.android.tron.domain.pojo.Trailer

/**
 * Created by osmanboy on 2/22/2022.
 */

private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/"
private const val SMALL_POSTER_SIZE = "w185"
private const val BIG_POSTER_SIZE = "w780"

//Для отзывов
private const val KEY_AUTHOR = "author"
private const val KEY_CONTENT = "content"

//Для видео
private const val KEY_KEY_OF_VIDEO = "key"
private const val KEY_NAME = "name"
private const val YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v="

private const val KEY_RESULTS = "results"
private const val KEY_ID = "id"
private const val KEY_VOTE_COUNT = "vote_count"
private const val KEY_TITLE: String = "title"
private const val KEY_ORIGINAL_TITLE: String = "original_title"
private const val KEY_OVERVIEW: String = "overview"
private const val KEY_POSTER_PATH: String = "poster_path"
private const val KEY_BACKDROP_PATH: String = "backdrop_path"
private const val KEY_VOTE_AVERAGE = "vote_average"
private const val KEY_RELEASE_DATA: String = "release_date"

fun List<MovieRemote>.remoteMovieToMovie(): List<Movie> {
    return this.map {
        Movie(
            id = it.id,
            backdrop_path = it.backdrop_path,
            genre_ids = it.genre_ids,
            original_title = it.original_title,
            overview = it.overview,
            poster_path = BASE_POSTER_URL + SMALL_POSTER_SIZE + it.poster_path,
            release_date = it.release_date,
            title = it.title,
            vote_average = it.vote_average,
            big_poster_path = BASE_POSTER_URL + BIG_POSTER_SIZE + it.poster_path,
            vote_count = it.vote_count
        )
    }
}

fun List<TrailerRemote>.remoteTrailerToTrailer(): List<Trailer> {
    return this.map {
        Trailer(key = YOUTUBE_BASE_URL + it.key, name = it.name)
    }
}

fun List<ReviewRemote>.remoteReviewToReview(): List<Review> {
    return this.map {
        Review(author = it.author, content = it.content)
    }
}

