package kz.android.tron.data.network.paging

import kz.android.tron.data.mapper.movieDtoListToMovieList
import kz.android.tron.data.network.retrofit.ApiService
import kz.android.tron.domain.model.Movie

class MovieListDataSource(private val service: ApiService, private val sortBy: String) : BasePagingSource<Movie>() {

    override suspend fun mapData(): List<Movie> {
        val response = service.getAllMovies(sortBy = sortBy, page = pageNumber)
        return response.body()?.results.movieDtoListToMovieList()
    }
}

