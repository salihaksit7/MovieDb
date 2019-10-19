package com.salihaksit.moviedb.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideSharedPref(context: Context): SharedPreferences {
        return context.getSharedPreferences("myMovieDbPref", Context.MODE_PRIVATE)
    }

}