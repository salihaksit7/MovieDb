package com.salihaksit.moviedb.di.module

import dagger.Module

@Module(includes = [DatabaseModule::class, NetworkModule::class])
class RepositoryModule {

}