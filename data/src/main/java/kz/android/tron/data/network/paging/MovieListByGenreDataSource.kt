package kz.android.tron.data.network.paging

import kz.android.tron.data.mapper.MovieListMapper
import kz.android.tron.data.network.retrofit.ApiService
import kz.android.tron.domain.model.Movie

class MovieListByGenreDataSource(private val service: ApiService, val id: Int) : BasePagingSource<Movie>() {

    override suspend fun mapData(): List<Movie> {
        val response = service.getMoviesByGenre(genreId = id, page = pageNumber)
        return MovieListMapper.map(response.body()?.results)
    }
}

