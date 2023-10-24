package kz.android.tron.data.mapper

import kz.android.tron.data.network.dto.MovieDto
import kz.android.tron.domain.model.Movie


const val DEFAULT_EMPTY_STRING = ""
const val DEFAULT_ZERO_INT = 0
const val DEFAULT_ZERO_DOUBLE = 0.0

const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/"
const val SMALL_POSTER_SIZE = "w185"
const val BIG_POSTER_SIZE = "w780"

fun List<MovieDto>?.movieDtoListToMovieList(): List<Movie> {
    return this?.map {
        it.movieDtoToMovie()
    } ?: emptyList()
}

private fun Double.doubleToInt() = this.toString().replace(".", "").toInt()

fun MovieDto.movieDtoToMovie() =
    Movie(
            id = this.id ?: DEFAULT_ZERO_INT,

            backdropPath = BASE_POSTER_URL + BIG_POSTER_SIZE + this.backdropPath,

            genreId = this.genreIds ?: emptyList(),

            originalTitle = this.originalTitle ?: DEFAULT_EMPTY_STRING,

            overview = this.overview ?: DEFAULT_EMPTY_STRING,

            posterPath = BASE_POSTER_URL + SMALL_POSTER_SIZE + this.posterPath,

            releaseDate = this.releaseDate ?: DEFAULT_EMPTY_STRING,

            title = this.title ?: DEFAULT_EMPTY_STRING,

            voteAverage = (this.voteAverage ?: DEFAULT_ZERO_DOUBLE).doubleToInt(),

            bigPosterPath = BASE_POSTER_URL + BIG_POSTER_SIZE + this.posterPath,

            voteCount = this.voteCount ?: DEFAULT_ZERO_INT
    )




