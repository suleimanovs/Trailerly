package kz.android.tron.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kz.android.tron.domain.usecase.GetMoviesByGenreUseCase
import javax.inject.Inject

class GenreContentViewModel @Inject constructor(private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase) : ViewModel() {

     fun getMoviesByGenre(genreId: Int) = getMoviesByGenreUseCase.invoke(genreId)

}