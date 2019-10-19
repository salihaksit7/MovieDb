package com.salihaksit.moviedb.data.repository

import com.salihaksit.moviedb.base.adapter.BaseRecyclerAdapter
import com.salihaksit.moviedb.base.adapter.ItemClickListener
import com.salihaksit.moviedb.data.MovieListType
import com.salihaksit.moviedb.data.layoutmodel.MovieClusterLayoutModel
import com.salihaksit.moviedb.data.layoutmodel.MovieLayoutModel
import com.salihaksit.moviedb.network.Api
import com.salihaksit.moviedb.network.models.MovieResponseModel
import com.salihaksit.moviedb.utils.OnBottomReachedListener
import io.reactivex.Observable
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: Api
) {

    fun getMovies(page: Int, type: String): Observable<MovieResponseModel> {
        return api.getMovies(page, type)
    }

    fun getMovieClusterList(
        listener: ItemClickListener<MovieLayoutModel>,
        onBottomReachedListener: OnBottomReachedListener<MovieLayoutModel>
    ): MutableList<MovieClusterLayoutModel> {
        val movieClusterList = arrayListOf<MovieClusterLayoutModel>()
        MovieListType.values().forEach {
            movieClusterList.add(
                MovieClusterLayoutModel(
                    header = it.header,
                    adapter = BaseRecyclerAdapter(listener, onBottomReachedListener)
                )
            )
        }
        return movieClusterList
    }
}