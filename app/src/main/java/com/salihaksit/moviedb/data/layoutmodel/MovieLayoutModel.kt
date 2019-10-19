package com.salihaksit.moviedb.data.layoutmodel

import com.salihaksit.moviedb.R
import com.salihaksit.moviedb.base.adapter.LayoutModel
import com.salihaksit.moviedb.data.MovieListType
import java.io.Serializable

data class MovieLayoutModel(
    val imageUrl: String,
    val detailImage: String?,
    val overview: String?,
    val title: String?,
    val voteCount: String,
    val type: MovieListType
) : Serializable, LayoutModel {
    override fun layoutId(): Int {
        return R.layout.item_movie
    }

}