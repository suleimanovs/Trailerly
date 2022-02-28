package kz.android.tron.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import kz.android.tron.data.network.ApiFactory
import kz.android.tron.data.repository.MovieRepositoryImpl
import kz.android.tron.domain.MovieRepository

/**
 * Created by osmanboy on 2/26/2022.
 */
@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService()= ApiFactory.apiService

    }

}