package com.salihaksit.moviedb.di.module

import com.salihaksit.moviedb.data.repository.MovieRepository
import com.salihaksit.moviedb.usecase.MovieUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
class UseCaseModule {

    @Provides
    @Singleton
    fun provideMovieUseCase(movieRepository: MovieRepository): MovieUseCase {
        return MovieUseCase(movieRepository)
    }

}