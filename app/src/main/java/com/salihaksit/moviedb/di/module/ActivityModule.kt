package com.salihaksit.moviedb.di.module

import com.salihaksit.moviedb.ui.MainActivity
import com.salihaksit.moviedb.ui.MovieDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMovieDetailActivity(): MovieDetailActivity
}