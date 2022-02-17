package com.android.tron.domain.usecase

import android.graphics.Movie
import androidx.lifecycle.LiveData
import com.android.tron.domain.MovieRepository

/**
 * Created by osmanboy on 2/17/2022.
 */

class GetMovieByIdUseCase(private val movieRepository: MovieRepository) {

    operator fun invoke(id:Int):LiveData<Movie>{
       return movieRepository.getMovieById(id)
    }

}