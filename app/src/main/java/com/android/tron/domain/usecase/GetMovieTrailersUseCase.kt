package com.android.tron.domain.usecase

import androidx.lifecycle.LiveData
import com.android.tron.domain.MovieRepository
import com.android.tron.domain.pojo.Trailer

/**
 * Created by osmanboy on 2/22/2022.
 */
class GetMovieTrailersUseCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(id:Int): List<Trailer> {
        return movieRepository.getMovieTrailer(id)
    }
}