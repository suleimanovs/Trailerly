package kz.android.tron.domain.usecase

import kz.android.tron.domain.pojo.Movie
import kz.android.tron.domain.MovieRepository
import javax.inject.Inject

/**
 * Created by osmanboy on 2/22/2022.
 */
class GetTopRatedMoviesUseCase  @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(id: Int): List<Movie> {
        return movieRepository.getTopRatedMovies(id)
    }
}