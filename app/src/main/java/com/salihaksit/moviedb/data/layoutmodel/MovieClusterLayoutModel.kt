package com.salihaksit.moviedb.data.layoutmodel

import com.salihaksit.moviedb.R
import com.salihaksit.moviedb.base.adapter.BaseRecyclerAdapter
import com.salihaksit.moviedb.base.adapter.LayoutModel

data class MovieClusterLayoutModel(
    val header: String,
    var pagenumber : Int = 1,
    var totalPages : Int = 1,
    val adapter: BaseRecyclerAdapter<MovieLayoutModel>
) : LayoutModel{
    override fun layoutId(): Int {
        return R.layout.item_movie_cluster
    }

}