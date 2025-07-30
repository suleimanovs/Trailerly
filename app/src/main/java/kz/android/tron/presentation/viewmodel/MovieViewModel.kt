package kz.android.tron.presentation.viewmodel

import androidx.lifecycle.*
import androidx.paging.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kz.android.tron.domain.model.Movie
import kz.android.tron.domain.model.Trailer
import kz.android.tron.domain.usecase.GetMovieListUseCase
import kz.android.tron.domain.usecase.GetMovieTrailersUseCase
import javax.inject.Inject

sealed class MovieListState {
    object Loading : MovieListState()
    data class Success(val movies: PagingData<Movie>) : MovieListState()
    data class Error(val message: String) : MovieListState()
}

sealed class TrailerState {
    object Loading : TrailerState()
    data class Success(val trailers: PagingData<Trailer>) : TrailerState()
    data class Error(val message: String) : TrailerState()
}

class MovieViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val getMovieTrailersUseCase: GetMovieTrailersUseCase,
) : ViewModel() {

    private val _movieListState = MutableStateFlow<MovieListState>(MovieListState.Loading)
    val movieListState: StateFlow<MovieListState> = _movieListState.asStateFlow()

    private val _trailerState = MutableStateFlow<TrailerState>(TrailerState.Loading)
    val trailerState: StateFlow<TrailerState> = _trailerState.asStateFlow()

    fun getMovieList(sortBy: String): Flow<PagingData<Movie>> {
        return getMovieListUseCase(sortBy)
            .onStart { _movieListState.value = MovieListState.Loading }
            .catch { error ->
                _movieListState.value = MovieListState.Error(error.message ?: "Unknown error")
            }
            .onEach { pagingData ->
                _movieListState.value = MovieListState.Success(pagingData)
            }
    }

    fun getMovieTrailers(id: Int): Flow<PagingData<Trailer>> {
        return getMovieTrailersUseCase(id)
            .onStart { _trailerState.value = TrailerState.Loading }
            .catch { error ->
                _trailerState.value = TrailerState.Error(error.message ?: "Unknown error")
            }
            .onEach { pagingData ->
                _trailerState.value = TrailerState.Success(pagingData)
            }
    }

    fun refreshMovieList(sortBy: String) {
        viewModelScope.launch {
            getMovieList(sortBy).collect()
        }
    }

    fun refreshTrailers(id: Int) {
        viewModelScope.launch {
            getMovieTrailers(id).collect()
        }
    }

    fun clearStates() {
        _movieListState.value = MovieListState.Loading
        _trailerState.value = TrailerState.Loading
    }
}