package kz.android.tron.data.network.paging

import kz.android.tron.data.mapper.ReviewListMapper
import kz.android.tron.data.network.retrofit.ApiService
import kz.android.tron.domain.model.Review

class ReviewListDataSource(
    private val service: ApiService,
    val id: Int,
) : BasePagingSource<Review>() {

    override suspend fun mapData(): List<Review> {
        val response = service.getMovieReviewsById(id = id)
        return ReviewListMapper.map(response.body()?.results)
    }
}