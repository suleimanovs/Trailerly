package kz.android.tron.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kz.android.tron.presentation.viewmodel.GenreContentViewModel
import kz.android.tron.presentation.viewmodel.MovieViewModel

/**
 * Created by osmanboy on 2/26/2022.
 */
@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(MovieViewModel::class)
    fun movieViewModel(impl: MovieViewModel): ViewModel


    @IntoMap
    @Binds
    @ViewModelKey(GenreContentViewModel::class)
    fun genreContentViewModel(impl: GenreContentViewModel): ViewModel
}