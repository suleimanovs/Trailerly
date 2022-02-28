package kz.android.tron.domain

import kz.android.tron.domain.pojo.Movie
import kz.android.tron.domain.pojo.Review
import kz.android.tron.domain.pojo.Trailer

/**
 * Created by osmanboy on 2/17/2022.
 */

interface MovieRepository {

    suspend fun getMovieList(sortBy: String, page: Int): List<Movie>

    suspend fun getMoviesByGenre(page: Int, genreId: Int): List<Movie>

    suspend fun getMovieById(id: Int): Movie

    suspend fun getPopularMovies(page: Int): List<Movie>

    suspend fun getTopRatedMovies(page: Int): List<Movie>

    suspend fun getMovieReview(id: Int): List<Review>

    suspend fun getMovieTrailer(id: Int): List<Trailer>
    // это фича на будущее
    suspend fun searchMovie(query: String): List<Movie>

}