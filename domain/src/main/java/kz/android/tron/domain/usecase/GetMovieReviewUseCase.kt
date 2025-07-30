package kz.android.tron.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kz.android.tron.domain.repository.MovieRepository
import kz.android.tron.domain.model.Review
import javax.inject.Inject


class GetMovieReviewUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(id: Int): Flow<PagingData<Review>> {
        return movieRepository.getMovieReview(id)
    }
}