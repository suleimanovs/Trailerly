package kz.android.tron.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kz.android.tron.data.mapper.movieDtoListToMovieList
import kz.android.tron.data.network.retrofit.ApiService
import kz.android.tron.domain.model.Movie

class SearchMovieListDataSource(val service: ApiService, val query: String) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        return try {
            val pageNumber = params.key ?: DEFAULT_PAGE
            val response = service.searchMovie(query = query)
            val responseData = mutableListOf<Movie>()
            val data = response.body()?.results.movieDtoListToMovieList()
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

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    companion object {
        private const val DEFAULT_PAGE = 1
    }
}

