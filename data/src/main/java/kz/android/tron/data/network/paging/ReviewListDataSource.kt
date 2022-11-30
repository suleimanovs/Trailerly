package kz.android.tron.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kz.android.tron.data.mapper.reviewDtoToReview
import kz.android.tron.data.network.retrofit.ApiService
import kz.android.tron.domain.model.Review



class ReviewListDataSource(val service: ApiService, val id: Int) : PagingSource<Int, Review>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {

        return try {
            val pageNumber = params.key ?: DEFAULT_PAGE
            val response = service.getMovieReviewsById(id = id)
            val responseData = mutableListOf<Review>()
            val data = response.body()?.results.reviewDtoToReview()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (pageNumber == DEFAULT_PAGE) null else pageNumber - 1,
                nextKey = if (data.isNotEmpty()) pageNumber.plus(1) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return null
    }

    companion object {
        private const val DEFAULT_PAGE = 1
    }
}

