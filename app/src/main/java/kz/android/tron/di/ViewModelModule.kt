package kz.android.tron.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kz.android.tron.presentation.viewmodel.GenreContentViewModel
import kz.android.tron.presentation.viewmodel.MovieListViewModel

/**
 * Created by osmanboy on 2/26/2022.
 */
@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(MovieListViewModel::class)
    fun movieViewModel(impl: MovieListViewModel): ViewModel


    @IntoMap
    @Binds
    @ViewModelKey(GenreContentViewModel::class)
    fun genreContentViewModel(impl: GenreContentViewModel): ViewModel
}