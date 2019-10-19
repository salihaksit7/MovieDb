package com.salihaksit.moviedb.data.viewdata

import com.salihaksit.moviedb.data.layoutmodel.MovieLayoutModel

data class MovieListViewData (
    val pageNumber : Int,
    val totalPages : Int,
    val itemList : MutableList<MovieLayoutModel> = arrayListOf()
)