package kz.android.tron.data.network.paging

import kz.android.tron.data.mapper.MovieListMapper
import kz.android.tron.data.network.retrofit.ApiService
import kz.android.tron.domain.model.Movie
import kz.android.tron.domain.repository.BaseRepository
import kz.android.tron.domain.repository.mappedApiCall

class MovieListDataSource(private val service: ApiService, private val sortBy: String) : BasePagingSource<Movie>(), BaseRepository {

    override suspend fun mapData(): Result<List<Movie>> {
        return mappedApiCall(MovieListMapper) { service.getAllMovies(sortBy = sortBy, page = pageNumber).results }
    }
}

