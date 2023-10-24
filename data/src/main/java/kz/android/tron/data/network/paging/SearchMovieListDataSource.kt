package kz.android.tron.data.network.paging

import kz.android.tron.data.mapper.movieDtoListToMovieList
import kz.android.tron.data.network.retrofit.ApiService
import kz.android.tron.domain.model.Movie

class SearchMovieListDataSource(private val service: ApiService, private val query: String) : BasePagingSource<Movie>() {

    override suspend fun mapData(): List<Movie> {
        val response = service.searchMovie(query = query)
        return response.body()?.results.movieDtoListToMovieList()
    }
}

