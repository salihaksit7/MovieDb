package com.salihaksit.moviedb.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.salihaksit.moviedb.di.ViewModelFactory
import com.salihaksit.moviedb.ui.MainVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainVM::class)
    internal abstract fun vmMain(vmMain: MainVM): ViewModel
}