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

        backdropPath = BASE_POSTER_URL + BIG_POSTER_SIZE + this.backdrop_path,

        genreId = this.genre_ids ?: emptyList(),

        originalTitle = this.original_title ?: DEFAULT_EMPTY_STRING,

        overview = this.overview ?: DEFAULT_EMPTY_STRING,

        posterPath = BASE_POSTER_URL + SMALL_POSTER_SIZE + this.poster_path,

        releaseDate = this.release_date ?: DEFAULT_EMPTY_STRING,

        title = this.title ?: DEFAULT_EMPTY_STRING,

        voteAverage = (this.vote_average ?: DEFAULT_ZERO_DOUBLE).doubleToInt(),

        bigPosterPath = BASE_POSTER_URL + BIG_POSTER_SIZE + this.poster_path,

        voteCount = this.vote_count ?: DEFAULT_ZERO_INT
    )




