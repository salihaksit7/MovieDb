package com.salihaksit.moviedb.di.component

import android.app.Application
import com.salihaksit.moviedb.App
import com.salihaksit.moviedb.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        UseCaseModule::class,
        RepositoryModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}