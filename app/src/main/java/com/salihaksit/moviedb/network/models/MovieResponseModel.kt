package com.salihaksit.moviedb.network.models

data class MovieResponseModel(
    val page: Int,
    val results: MutableList<Movie>,
    val total_pages: Int,
    val total_results: Int
)