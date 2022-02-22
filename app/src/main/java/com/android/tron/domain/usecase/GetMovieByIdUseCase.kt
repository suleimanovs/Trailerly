package com.android.tron.domain.usecase

import com.android.tron.domain.pojo.Movie
import com.android.tron.domain.MovieRepository

/**
 * Created by osmanboy on 2/17/2022.
 */

class GetMovieByIdUseCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(id:Int): Movie {
       return movieRepository.getMovieById(id)
    }

}