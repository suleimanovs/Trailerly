package kz.android.tron.data.network.paging

import kz.android.tron.data.mapper.MovieListMapper
import kz.android.tron.data.network.retrofit.ApiService
import kz.android.tron.domain.model.Movie
import kz.android.tron.domain.repository.BaseRepository
import kz.android.tron.domain.repository.mappedApiCall

class MovieListByGenreDataSource(private val service: ApiService, val id: Int) : BasePagingSource<Movie>(), BaseRepository {

    override suspend fun mapData(): Result<List<Movie>> {
        return mappedApiCall(MovieListMapper) { service.getMoviesByGenre(genreId = id, page = pageNumber).results }
    }
}

