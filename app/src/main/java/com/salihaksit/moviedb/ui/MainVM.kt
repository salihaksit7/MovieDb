package com.salihaksit.moviedb.ui

import android.view.View
import com.salihaksit.moviedb.base.BaseViewModel
import com.salihaksit.moviedb.base.adapter.BaseRecyclerAdapter
import com.salihaksit.moviedb.base.adapter.ItemClickListener
import com.salihaksit.moviedb.data.MovieListType
import com.salihaksit.moviedb.data.layoutmodel.MovieClusterLayoutModel
import com.salihaksit.moviedb.data.layoutmodel.MovieLayoutModel
import com.salihaksit.moviedb.usecase.MovieUseCase
import com.salihaksit.moviedb.utils.OnBottomReachedListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainVM @Inject constructor(private val movieUseCase: MovieUseCase) :
    BaseViewModel(),
    ItemClickListener<MovieLayoutModel>,
    OnBottomReachedListener<MovieLayoutModel> {

    private lateinit var movieClusterList: MutableList<MovieClusterLayoutModel>

    val adapter = BaseRecyclerAdapter<MovieClusterLayoutModel>(arrayListOf())

    fun getMovies() {
        movieClusterList = movieUseCase.getMovieClusterList(this, this)
        adapter.setList(movieClusterList)

        MovieListType.values().forEach {
            fetchMovies(it)
        }
    }

    private fun fetchMovies(type: MovieListType) {
        disposable = movieUseCase.getMovieList(movieClusterList[type.order].pagenumber, type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveStart() }
            .doOnTerminate { onRetrieveFinish() }
            .subscribe({
                movieClusterList[type.order].pagenumber = it.pageNumber
                movieClusterList[type.order].totalPages = it.totalPages
                movieClusterList[type.order].adapter.addAll(it.itemList)
            }, {

            })
    }

    override fun onItemClick(view: View, item: MovieLayoutModel) {

    }

    override fun onBottomReached(position: Int, item: MovieLayoutModel) {
        if (movieClusterList[item.type.order].pagenumber < movieClusterList[item.type.order].totalPages) {
            movieClusterList[item.type.order].pagenumber++
            fetchMovies(item.type)
        }
    }

}