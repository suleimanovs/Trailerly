package kz.android.tron.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
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

    private var trailerCurrentIndex = 0
    private var pageCount: Int = DEFAULT_PAGE

    private val _trailerList = MutableLiveData<List<Trailer>>()
    val trailerList: LiveData<List<Trailer>> get() = _trailerList

    fun getNextTrailerId(): String {
        trailerCurrentIndex = (trailerCurrentIndex + 1) % (_trailerList.value?.size ?: 0)
        return _trailerList.value?.get(trailerCurrentIndex)?.key ?: ""
    }


    fun getMovieList(sortBy: String) = flow {
        emit(getMovieListUseCase(sortBy, pageCount))
    }

    fun incrementPage() = pageCount++

    fun getMovieTrailers(id: Int) {
        viewModelScope.launch {
            getMovieTrailersUseCase(id).apply {
                _trailerList.postValue(this)
            }
        }
    }

    companion object {
        private const val DEFAULT_PAGE = 1
    }

}