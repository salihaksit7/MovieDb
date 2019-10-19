package com.salihaksit.moviedb.data

enum class MovieListType(val header: String, val sortType: String, val order: Int) {
    TopRated("Top Rated", "vote_count.desc", 0),
    Popular("Popular", "popularity.desc", 1),
    Revenue("Revenue", "revenue.desc", 2),
}