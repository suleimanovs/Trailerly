package kz.android.tron.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kz.android.tron.domain.repository.MovieRepository
import kz.android.tron.domain.model.Trailer
import javax.inject.Inject


class GetMovieTrailersUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(id: Int): Flow<PagingData<Trailer>> {
        return movieRepository.getMovieTrailer(id)
    }
}