package com.salihaksit.moviedb.di.module

import com.salihaksit.moviedb.data.repository.MovieRepository
import com.salihaksit.moviedb.network.Api
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, NetworkModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(api: Api): MovieRepository {
        return MovieRepository(api)
    }

}