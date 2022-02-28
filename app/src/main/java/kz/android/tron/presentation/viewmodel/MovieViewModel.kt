package kz.android.tron.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kz.android.tron.domain.pojo.Movie
import kz.android.tron.domain.pojo.Trailer
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

    private val scope = CoroutineScope(Dispatchers.IO)

    fun incrementPageCount() {
        ++page
    }

    fun getAllMovies(sortBy: String, page: Int) = liveData<List<Movie>>(scope.coroutineContext) {
        emit(getMovieListUseCase(sortBy, page))
    }

    fun getMovieTrailers(id: Int) = liveData<List<Trailer>>(scope.coroutineContext) {
        emit(getMovieTrailersUseCase(id))
    }

    fun getPopularMovies(page: Int) = liveData<List<Movie>>(scope.coroutineContext) {
        emit(getPopularMoviesUseCase(page))
    }


    fun getTopRated(page: Int) = liveData<List<Movie>>(scope.coroutineContext) {
        emit(getTopRatedMoviesUseCase(page))
    }

    fun getMoviesByGenre(genreId: Int, page: Int) = liveData<List<Movie>>(scope.coroutineContext) {
        emit(getMoviesByGenreUseCase(page, genreId))

    }

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }
}