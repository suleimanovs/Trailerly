package kz.android.tron.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.android.tron.domain.model.Movie
import kz.android.tron.domain.usecase.GetMovieByGenreUseCase
import javax.inject.Inject

/**
 * Created by osmanboy on 3/4/2022.
 */
class GenreContentViewModel @Inject constructor(private val getMoviesByGenreUseCase: GetMovieByGenreUseCase) :
    ViewModel() {


    private val _movieGenreList = MutableLiveData<List<Movie>>()
    val movieGenreList: LiveData<List<Movie>> get() = _movieGenreList

    private var pageCount: Int = 1


    fun getMoviesByGenre(genreId: Int) {
        viewModelScope.launch {
            getMoviesByGenreUseCase(pageCount++, genreId).apply {

                val movieList = mutableListOf<Movie>().also { newList ->
                    movieGenreList.value?.let { newList.addAll(it) }
                    newList.addAll(this)
                }

                _movieGenreList.postValue(movieList)
            }
        }
    }

}