package kz.android.tron.data.repository

import kz.android.tron.data.mapper.movieDtoListToMovieList
import kz.android.tron.data.mapper.movieDtoToMovie
import kz.android.tron.data.mapper.reviewDtoToReview
import kz.android.tron.data.mapper.trailerDtoToTrailer
import kz.android.tron.data.network.ApiService
import kz.android.tron.domain.MovieRepository
import kz.android.tron.domain.model.Movie
import kz.android.tron.domain.model.Review
import kz.android.tron.domain.model.Trailer
import javax.inject.Inject

/**
 * Created by osmanboy on 2/22/2022.
 */
class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService): MovieRepository {

    override suspend fun getMovieList(sortBy: String, page: Int): List<Movie> {
        return apiService.getAllMovies(
            sortBy = sortBy,
            page = page
        ).results.movieDtoListToMovieList()
    }

    override suspend fun getMovieById(id: Int): Movie {
        return apiService.getMovieById(id).movieDtoToMovie()
    }

    override suspend fun searchMovie(query: String): List<Movie> {
        return apiService.searchMovie(query = query).results.movieDtoListToMovieList()

    }

    override suspend fun getMovieReview(id: Int): List<Review> {
        return apiService.getMovieReviewsById(id).results.reviewDtoToReview()

    }

    override suspend fun getMovieTrailer(id: Int): List<Trailer> {
        return apiService.getMovieTrailersById(id).results.trailerDtoToTrailer()
    }

    override suspend fun getMoviesByGenre(page: Int, genreId: Int): List<Movie> {
        return apiService.getMoviesByGenre(
            page = page,
            genreId = genreId,
        ).results.movieDtoListToMovieList()
    }
}