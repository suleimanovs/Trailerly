package kz.android.tron.domain.usecase

import kz.android.tron.domain.repository.MovieRepository
import kz.android.tron.domain.model.Movie
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(id: Int): Result<Movie> {
        return movieRepository.getMovieById(id)
    }
}