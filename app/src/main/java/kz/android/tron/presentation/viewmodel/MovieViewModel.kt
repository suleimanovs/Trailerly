package kz.android.tron.presentation.viewmodel

import androidx.lifecycle.*
import androidx.paging.map
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kz.android.tron.domain.model.Trailer
import kz.android.tron.domain.usecase.GetMovieListUseCase
import kz.android.tron.domain.usecase.GetMovieTrailersUseCase
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val getMovieTrailersUseCase: GetMovieTrailersUseCase,
) : ViewModel() {


    fun getMovieList(sortBy: String) = (getMovieListUseCase(sortBy))

    fun getMovieTrailers(id: Int) = getMovieTrailersUseCase(id)


}