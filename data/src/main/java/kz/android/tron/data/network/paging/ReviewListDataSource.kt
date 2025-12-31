package kz.android.tron.data.network.paging

import kz.android.tron.data.mapper.ReviewListMapper
import kz.android.tron.data.network.retrofit.ApiService
import kz.android.tron.domain.model.Review
import kz.android.tron.domain.repository.BaseRepository
import kz.android.tron.domain.repository.mappedApiCall

class ReviewListDataSource(private val service: ApiService, private val id: Int) : BasePagingSource<Review>(), BaseRepository {

    override suspend fun mapData(): Result<List<Review>> {
        return mappedApiCall(ReviewListMapper) { service.getMovieReviewsById(id = id).results }
    }
}