package com.salihaksit.moviedb.network

import com.salihaksit.moviedb.network.models.MovieResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("discover/movie")
    fun getMovies(
        @Query("page") page: Int,
        @Query("sort_by") type: String
    ): Observable<MovieResponseModel>
}