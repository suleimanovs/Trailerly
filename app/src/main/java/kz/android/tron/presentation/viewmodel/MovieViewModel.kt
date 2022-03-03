package kz.android.tron.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.flow
import kz.android.tron.domain.model.Movie
import kz.android.tron.domain.model.Trailer
import kz.android.tron.domain.usecase.*
import javax.inject.Inject

/**
 * Created by osmanboy on 2/22/2022.
 */
class MovieViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val getMovieTrailersUseCase: GetMovieTrailersUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getMoviesByGenreUseCase: GetMovieByGenreUseCase
) : ViewModel() {

    var page: Int = 1

    val trailers = mutableListOf<Trailer>()
    private var trailerCurrentIndex = 0
    fun getNextTrailerId(): String {
        trailerCurrentIndex = (trailerCurrentIndex + 1) % trailers.size
        return trailers[trailerCurrentIndex].key
    }


    fun incrementPageCount() = ++page


    fun getAllMovies(sortBy: String, page: Int) = flow<List<Movie>> {
            emit(getMovieListUseCase(sortBy, page))
        }

    fun getMovieTrailers(id: Int) = flow<List<Trailer>> {
        emit(getMovieTrailersUseCase(id))
    }

    fun getPopularMovies(page: Int) = flow<List<Movie>> {
        emit(getPopularMoviesUseCase(page))
    }

    fun getTopRated(page: Int) = flow<List<Movie>> {
        emit(getTopRatedMoviesUseCase(page))
    }

    fun getMoviesByGenre(genreId: Int, page: Int) = flow<List<Movie>> {
            emit(getMoviesByGenreUseCase(page, genreId))
        }


}