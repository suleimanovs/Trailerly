package com.android.tron.data.repository

import com.android.tron.data.api.remoteMovieToMovie
import com.android.tron.data.api.remoteReviewToReview
import com.android.tron.data.api.remoteTrailerToTrailer
import com.android.tron.domain.pojo.Movie
import com.android.tron.domain.MovieRepository
import com.android.tron.domain.pojo.Review
import com.android.tron.domain.pojo.Trailer
import com.osmanboy.mymovie.api.ApiService

/**
 * Created by osmanboy on 2/22/2022.
 */
class MovieRepositoryImpl(private val apiService: ApiService) : MovieRepository {

    override suspend fun getMovieList (page: Int): List<Movie> {
        return apiService.getAllMovies(page = page).results.remoteMovieToMovie()
    }

    override suspend fun getMovieById(id: Int): Movie {
       return apiService.getMovieById(id)
    }

    override suspend fun searchMovie(query: String): List<Movie> {
        return apiService.searchMovie(query = query).results.remoteMovieToMovie()

    }

    override suspend fun getPopularMovies(page: Int): List<Movie> {
        return apiService.getPopularMovies(page = page).results.remoteMovieToMovie()

    }

    override suspend fun getTopRatedMovies(page: Int): List<Movie> {
        return apiService.getTopRatedMovies(page = page).results.remoteMovieToMovie()
    }

    override suspend fun getMovieReview(id: Int): List<Review> {
        return apiService.getMovieReviewsById(id).results.remoteReviewToReview()

    }

    override suspend fun getMovieTrailer(id: Int): List<Trailer> {
        return apiService.getMovieTrailersById(id).results.remoteTrailerToTrailer()
    }

}