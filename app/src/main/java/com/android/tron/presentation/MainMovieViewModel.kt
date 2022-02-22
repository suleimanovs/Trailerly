package com.android.tron.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.android.tron.data.api.RetrofitBuilder
import com.android.tron.data.repository.MovieRepositoryImpl
import com.android.tron.domain.MovieRepository
import com.android.tron.domain.pojo.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

/**
 * Created by osmanboy on 2/22/2022.
 */
class MainMovieViewModel() : ViewModel() {

    private val repository: MovieRepository =
        MovieRepositoryImpl(RetrofitBuilder.getInstance().apiService())
    private val scope = CoroutineScope(Dispatchers.IO)

    fun getAllMovies(page: Int) = liveData<List<Movie>>(scope.coroutineContext) {
        emit(repository.getMovieList(page))
    }


    fun searchMovie(query: String) = liveData<List<Movie>>(scope.coroutineContext) {
        emit(repository.searchMovie(query))
    }


    fun getPopularMovies(page: Int) = liveData<List<Movie>>(scope.coroutineContext) {
        emit(repository.getPopularMovies(page))
    }


    fun getTopRated(page: Int) = liveData<List<Movie>>(scope.coroutineContext) {
        emit(repository.getTopRatedMovies(page))
    }

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }
}