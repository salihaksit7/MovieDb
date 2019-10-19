package com.salihaksit.moviedb.data.layoutmodel

import com.salihaksit.moviedb.R
import com.salihaksit.moviedb.base.adapter.LayoutModel
import com.salihaksit.moviedb.data.MovieListType

data class MovieLayoutModel(
    val imageUrl: String,
    val type : MovieListType
) : LayoutModel{
    override fun layoutId(): Int {
        return R.layout.item_movie
    }

}