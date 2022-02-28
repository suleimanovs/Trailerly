package kz.android.tron.data.repository

import kz.android.tron.data.network.*
import kz.android.tron.domain.MovieRepository
import kz.android.tron.domain.pojo.Movie
import kz.android.tron.domain.pojo.Review
import kz.android.tron.domain.pojo.Trailer
import javax.inject.Inject

/**
 * Created by osmanboy on 2/22/2022.
 */
class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MovieRepository {

    override suspend fun getMovieList(sortBy: String, page: Int): List<Movie> {
        return apiService.getAllMovies(
            sortBy = sortBy,
            page = page
        ).results.remoteMovieListToMovieList()
    }

    override suspend fun getMovieById(id: Int): Movie {
        return apiService.getMovieById(id).remoteMovieToMovie()
    }

    override suspend fun searchMovie(query: String): List<Movie> {
        return apiService.searchMovie(query = query).results.remoteMovieListToMovieList()

    }

    override suspend fun getPopularMovies(page: Int): List<Movie> {
        return apiService.getPopularMovies(page = page).results.remoteMovieListToMovieList()

    }

    override suspend fun getTopRatedMovies(page: Int): List<Movie> {
        return apiService.getTopRatedMovies(page = page).results.remoteMovieListToMovieList()
    }

    override suspend fun getMovieReview(id: Int): List<Review> {
        return apiService.getMovieReviewsById(id).results.remoteReviewToReview()

    }

    override suspend fun getMovieTrailer(id: Int): List<Trailer> {
        return apiService.getMovieTrailersById(id).results.remoteTrailerToTrailer()
    }

    override suspend fun getMoviesByGenre(page: Int, genreId: Int): List<Movie> {
        return apiService.getMoviesByGenre(
            page = page,
            genreId = genreId,
        ).results.remoteMovieListToMovieList()
    }
}