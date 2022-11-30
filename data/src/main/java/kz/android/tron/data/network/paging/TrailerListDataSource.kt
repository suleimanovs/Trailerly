package kz.android.tron.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kz.android.tron.data.mapper.trailerDtoToTrailer
import kz.android.tron.data.network.retrofit.ApiService
import kz.android.tron.domain.model.Trailer

class TrailerListDataSource(private val service: ApiService, val id: Int) : PagingSource<Int, Trailer>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Trailer> {

        return try {
            val pageNumber = params.key ?: DEFAULT_PAGE
            val response = service.getMovieTrailersById(id = id)
            val responseData = mutableListOf<Trailer>()
            val data = response.body()?.results.trailerDtoToTrailer()
            responseData.addAll(data)

            val prevKey = if (pageNumber == DEFAULT_PAGE) null else pageNumber - 1
            val nextKey =  null // у этой апишки нет пагинаций, получаем все за раз

            LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Trailer>): Int? {
        return null
    }

    companion object {
        private const val DEFAULT_PAGE = 1
    }
}

