package kz.android.tron.domain.usecase

import kz.android.tron.domain.MovieRepository
import kz.android.tron.domain.pojo.Movie
import javax.inject.Inject

/**
 * Created by osmanboy on 2/17/2022.
 */

class GetMovieByGenreUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(page: Int, genreId: Int): List<Movie> {
        return movieRepository.getMoviesByGenre(page, genreId)
    }

}