package kz.android.tron.data.mapper

import kz.android.tron.data.network.BASE_POSTER_URL
import kz.android.tron.data.network.BIG_POSTER_SIZE
import kz.android.tron.data.network.SMALL_POSTER_SIZE
import kz.android.tron.data.network.model.MovieDto
import kz.android.tron.domain.pojo.Movie


/**
 * Created by osmanboy on 2/22/2022.
 */
const val DEFAULT_EMPTY_STRING = ""
const val DEFAULT_ZERO_INT = 0
const val DEFAULT_ZERO_DOUBLE = 0.0

fun List<MovieDto>?.movieDtoListToMovieList(): List<Movie> {
    return this?.map {
        it.movieDtoToMovie()
    } ?: emptyList()
}

private fun Double.doubleToInt() = this.toString().replace(".", "").toInt()


fun MovieDto.movieDtoToMovie() =
    Movie(
        id = this.id ?: DEFAULT_ZERO_INT,

        backdrop_path = BASE_POSTER_URL + BIG_POSTER_SIZE + this.backdrop_path,

        genre_ids = this.genre_ids ?: emptyList(),

        original_title = this.original_title ?: DEFAULT_EMPTY_STRING,

        overview = this.overview ?: DEFAULT_EMPTY_STRING,

        poster_path = BASE_POSTER_URL + SMALL_POSTER_SIZE + this.poster_path,

        release_date = this.release_date ?: DEFAULT_EMPTY_STRING,

        title = this.title ?: DEFAULT_EMPTY_STRING,

        vote_average = (this.vote_average ?: DEFAULT_ZERO_DOUBLE).doubleToInt(),

        big_poster_path = BASE_POSTER_URL + BIG_POSTER_SIZE + this.poster_path,

        vote_count = this.vote_count ?: DEFAULT_ZERO_INT
    )




