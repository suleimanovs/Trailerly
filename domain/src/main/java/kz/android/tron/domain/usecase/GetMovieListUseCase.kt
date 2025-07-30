package kz.android.tron.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kz.android.tron.domain.repository.MovieRepository
import kz.android.tron.domain.model.Movie
import javax.inject.Inject


class GetMovieListUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(sortBy: String): Flow<PagingData<Movie>> {
        return movieRepository.getMovieList(sortBy)
    }
}
