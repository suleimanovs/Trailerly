package kz.android.tron.data.mapper

import kz.android.tron.data.network.BASE_POSTER_URL
import kz.android.tron.data.network.BIG_POSTER_SIZE
import kz.android.tron.data.network.SMALL_POSTER_SIZE
import kz.android.tron.data.network.model.MovieDto
import kz.android.tron.data.network.model.ReviewDto
import kz.android.tron.data.network.model.TrailerDto
import kz.android.tron.domain.pojo.Movie
import kz.android.tron.domain.pojo.Review
import kz.android.tron.domain.pojo.Trailer


/**
 * Created by osmanboy on 2/22/2022.
 */

fun List<MovieDto>.movieDtoListToMovieList(): List<Movie> {
    return this.map {
        Movie(
            id = it.id,
            backdrop_path = BASE_POSTER_URL + BIG_POSTER_SIZE + it.backdrop_path,
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

fun MovieDto.movieDtoToMovie(): Movie {
    return this.let{
        Movie(
            id = it.id,
            backdrop_path = BASE_POSTER_URL + BIG_POSTER_SIZE + it.backdrop_path,
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




