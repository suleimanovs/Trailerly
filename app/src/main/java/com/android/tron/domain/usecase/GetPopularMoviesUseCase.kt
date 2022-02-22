package com.android.tron.domain.usecase

import com.android.tron.domain.pojo.Movie
import com.android.tron.domain.MovieRepository

/**
 * Created by osmanboy on 2/22/2022.
 */
class GetPopularMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(id: Int): List<Movie> {
        return movieRepository.getPopularMovies(id)
    }
}