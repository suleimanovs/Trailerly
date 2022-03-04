package kz.android.tron.domain

import kz.android.tron.domain.model.Movie
import kz.android.tron.domain.model.Review
import kz.android.tron.domain.model.Trailer

/**
 * Created by osmanboy on 2/17/2022.
 */

interface MovieRepository {

    suspend fun getMovieById(id: Int): Movie

    suspend fun searchMovie(query: String): List<Movie>     // это фича на будущее

    suspend fun getMovieReview(id: Int): List<Review>

    suspend fun getMovieTrailer(id: Int): List<Trailer>

    suspend fun getMovieList(sortBy: String, page: Int): List<Movie>

    suspend fun getMoviesByGenre(page: Int, genreId: Int): List<Movie>

}