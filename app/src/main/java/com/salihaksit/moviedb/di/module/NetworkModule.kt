package com.salihaksit.moviedb.di.module

import com.salihaksit.moviedb.BuildConfig
import com.salihaksit.moviedb.network.Api
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {


    @Singleton
    @Provides
    fun provideClientInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val url =
                request.url().newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .addQueryParameter("language", "en-US")
                    .build()
            request = request.newBuilder().url(url).build()
            return@Interceptor chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideRetrofitInterface(
        loggingInterceptor: HttpLoggingInterceptor,
        clientInterceptor: Interceptor
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(clientInterceptor)
                    .build()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }


    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

}