package kz.android.tron.di

import dagger.Component
import kz.android.tron.presentation.ui.login.SignInFragment
import kz.android.tron.presentation.ui.login.SignUpFragment
import kz.android.tron.presentation.ui.main.MovieDetailFragment
import kz.android.tron.presentation.ui.main.MovieListByFilterFragment
import kz.android.tron.presentation.ui.main.MovieListByGenreFragment
import kz.android.tron.presentation.ui.main.MovieListFragment
import kz.android.tron.presentation.ui.settings.DeleteAccountFragment

/**
 * Created by osmanboy on 2/26/2022.
 */
@[ApplicationScope Component(modules = [DataModule::class, ViewModelModule::class])]
interface AppComponent {

    fun inject(fragment: SignInFragment)

    fun inject(fragment: SignUpFragment)

    fun inject(fragment: DeleteAccountFragment)

    fun inject(fragment: MovieListFragment)

    fun inject(fragment: MovieListByFilterFragment)

    fun inject(fragment: MovieListByGenreFragment)

    fun inject(fragment: MovieDetailFragment)
}