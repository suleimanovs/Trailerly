package kz.android.tron.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kz.android.tron.data.mapper.MovieMapper
import kz.android.tron.data.mapper.TrailerListMapper
import kz.android.tron.data.mapper.TrailerMapper
import kz.android.tron.data.network.paging.MovieListByGenreDataSource
import kz.android.tron.data.network.paging.MovieListDataSource
import kz.android.tron.data.network.paging.ReviewListDataSource
import kz.android.tron.data.network.paging.SearchMovieListDataSource
import kz.android.tron.data.network.retrofit.ApiService
import kz.android.tron.domain.model.Movie
import kz.android.tron.domain.model.Review
import kz.android.tron.domain.model.Trailer
import kz.android.tron.domain.repository.MovieRepository
import kz.android.tron.domain.repository.mappedApiCall
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val service: ApiService) : MovieRepository {

    override fun getMovieList(sortBy: String): Flow<PagingData<Movie>> {
        return Pager(config) { MovieListDataSource(service, sortBy) }.flow
    }

    override suspend fun getMovieById(id: Int): Result<Movie> {
        return mappedApiCall(MovieMapper) { service.getMovieById(id) }
    }

    override fun searchMovie(query: String): Flow<PagingData<Movie>> {
        return Pager(config) { SearchMovieListDataSource(service, query) }.flow
    }

    override fun getMovieReview(id: Int): Flow<PagingData<Review>> {
        return Pager(config) { ReviewListDataSource(service, id) }.flow
    }

    override fun getMovieTrailer(id: Int): Flow<PagingData<Trailer>> = flow {
        val result = mappedApiCall(TrailerListMapper) { service.getMovieTrailersById(id).results }
        emit(PagingData.from(result.getOrDefault(emptyList())))
    }

    override fun getMoviesByGenre(genreId: Int): Flow<PagingData<Movie>> {
        return Pager(config) { MovieListByGenreDataSource(service, genreId) }.flow
    }

    companion object {
        private const val PAGE_SIZE = 10
        private val config = PagingConfig(pageSize = PAGE_SIZE)
    }
}