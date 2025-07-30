package kz.android.tron.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class BasePagingSource<T : Any> : PagingSource<Int, T>() {

    protected var pageNumber: Int = DEFAULT_PAGE

    abstract suspend fun mapData(): List<T>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {

        return try {

            pageNumber = params.key ?: DEFAULT_PAGE
            val data = mapData()

            LoadResult.Page(
                data = data,
                prevKey = if (pageNumber == DEFAULT_PAGE) null else pageNumber - 1,
                nextKey = if (data.isNotEmpty()) pageNumber.plus(1) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? = null

    companion object {

        internal const val DEFAULT_PAGE = 1
    }
}