package kz.android.tron.domain.usecase

import kz.android.tron.domain.MovieRepository
import kz.android.tron.domain.model.Review
import javax.inject.Inject

/**
 * Created by osmanboy on 2/22/2022.
 */
class GetMovieReviewUseCase  @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(id:Int): List<Review> {
        return movieRepository.getMovieReview(id)
    }
}