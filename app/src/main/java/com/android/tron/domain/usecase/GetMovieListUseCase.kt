package com.android.tron.domain.usecase

import android.graphics.Movie
import androidx.lifecycle.LiveData
import com.android.tron.domain.MovieRepository

/**
 * Created by osmanboy on 2/17/2022.
 */
class GetMovieListUseCase(private val movieRepository: MovieRepository) {

    operator fun invoke(): LiveData<List<Movie>> {
        return movieRepository.getMovieList()
    }
}
