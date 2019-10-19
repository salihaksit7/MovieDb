package com.salihaksit.moviedb.data


/*
    WARNING:
    [enum].values() always return the same order of elements,
    that's why we manually keep this order.
    E.g -> Let's say you want "Popular" comes first, you have to change :
        - places of TopRated and Popular
        - order parameter
 */

enum class MovieListType(val header: String, val sortType: String, val order: Int) {
    TopRated("Top Rated", "vote_count.desc", 0),
    Popular("Popular", "popularity.desc", 1),
    Revenue("Revenue", "revenue.desc", 2),
    Title("Original Title", "original_title.desc", 3),
}