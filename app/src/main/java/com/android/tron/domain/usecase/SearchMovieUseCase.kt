package com.android.tron.domain.usecase

import com.android.tron.domain.pojo.Movie
import com.android.tron.domain.MovieRepository

/**
 * Created by osmanboy on 2/17/2022.
 */

class SearchMovieUseCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(query: String): List<Movie> {
        return movieRepository.searchMovie(query)
    }
}