package com.salihaksit.moviedb.network.models

data class Movie(
    val backdrop_path: String?,
    val overview: String?,
    val poster_path: String?,
    val title: String?,
    val vote_count: Int
)