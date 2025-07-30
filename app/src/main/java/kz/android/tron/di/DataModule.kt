package kz.android.tron.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import kz.android.tron.data.network.retrofit.ApiFactory
import kz.android.tron.data.repository.MovieRepositoryImpl
import kz.android.tron.domain.repository.MovieRepository

/**
 * Created by osmanboy on 2/26/2022.
 */
@Module
interface DataModule {


    @[Binds ApplicationScope]
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    companion object {

        @[Provides ApplicationScope]
        fun provideApiService()= ApiFactory.apiService
    }

}