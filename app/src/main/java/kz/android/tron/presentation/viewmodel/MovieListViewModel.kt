package kz.android.tron.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.flow
import kz.android.tron.domain.model.Movie
import kz.android.tron.domain.model.Trailer
import kz.android.tron.domain.usecase.GetMovieListUseCase
import kz.android.tron.domain.usecase.GetMovieTrailersUseCase
import javax.inject.Inject

/**
 * Created by osmanboy on 2/22/2022.
 */
class MovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val getMovieTrailersUseCase: GetMovieTrailersUseCase,
) : ViewModel() {

    var page: Int = 1
    fun incrementPageCount() = ++page

    val trailers = mutableListOf<Trailer>()
    private var trailerCurrentIndex = 0
    fun getNextTrailerId(): String {
        trailerCurrentIndex = (trailerCurrentIndex + 1) % trailers.size
        return trailers[trailerCurrentIndex].key
    }


    fun getAllMovies(sortBy: String, page: Int) = flow<List<Movie>> {
        emit(getMovieListUseCase(sortBy, page))
    }

    fun getMovieTrailers(id: Int) = flow<List<Trailer>> {
        emit(getMovieTrailersUseCase(id))
    }

}