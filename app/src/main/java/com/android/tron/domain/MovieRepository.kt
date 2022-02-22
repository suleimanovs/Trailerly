package com.android.tron.domain

import com.android.tron.domain.pojo.Movie
import com.android.tron.domain.pojo.Review
import com.android.tron.domain.pojo.Trailer

/**
 * Created by osmanboy on 2/17/2022.
 */

interface MovieRepository {

    suspend fun getMovieList(page: Int): List<Movie>

    suspend fun getMovieById(id: Int): Movie

    suspend fun searchMovie(query: String): List<Movie>

    suspend fun getPopularMovies(page: Int): List<Movie>

    suspend fun getTopRatedMovies(page: Int): List<Movie>

    suspend fun getMovieReview(id: Int): List<Review>

    suspend fun getMovieTrailer(id: Int): List<Trailer>

}