package kz.android.tron.di

import dagger.Component
import kz.android.tron.presentation.ui.GenreContentFragment
import kz.android.tron.presentation.ui.MovieDetailFragment
import kz.android.tron.presentation.ui.MovieListFragment
import kz.android.tron.presentation.ui.MoviesContentFragment

/**
 * Created by osmanboy on 2/26/2022.
 */
@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(fragment: MovieListFragment)

    fun inject(fragment: MoviesContentFragment)

    fun inject(fragment: GenreContentFragment)

    fun inject(fragment: MovieDetailFragment)

}