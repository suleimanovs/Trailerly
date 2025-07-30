package kz.android.tron.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kz.android.tron.presentation.ui.login.SignInViewModel
import kz.android.tron.presentation.ui.login.SignUpViewModel
import kz.android.tron.presentation.ui.settings.DeleteAccountViewModel
import kz.android.tron.presentation.viewmodel.GenreContentViewModel
import kz.android.tron.presentation.viewmodel.MovieViewModel

/**
 * Created by osmanboy on 2/26/2022.
 */
@Module
interface ViewModelModule {

    @[IntoMap Binds ViewModelKey(MovieViewModel::class)]
    fun movieViewModel(impl: MovieViewModel): ViewModel

    @[IntoMap Binds ViewModelKey(SignInViewModel::class)]
    fun signInViewModel(impl: SignInViewModel): ViewModel

    @[IntoMap Binds ViewModelKey(SignUpViewModel::class)]
    fun signUpViewModel(impl: SignUpViewModel): ViewModel

    @[IntoMap Binds ViewModelKey(GenreContentViewModel::class)]
    fun genreContentViewModel(impl: GenreContentViewModel): ViewModel

    @[IntoMap Binds ViewModelKey(DeleteAccountViewModel::class)]
    fun deleteAccountViewModel(impl: DeleteAccountViewModel): ViewModel
}