package com.salihaksit.moviedb.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
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

    private var isTablet = false
    private lateinit var movieClusterList: MutableList<MovieClusterLayoutModel>
    lateinit var detailClickEvent: MutableLiveData<MovieLayoutModel>
    lateinit var movieDetailData: MutableLiveData<MovieLayoutModel>

    val adapter = BaseRecyclerAdapter<MovieClusterLayoutModel>(arrayListOf())

    fun setTablet(isTablet: Boolean) {
        this.isTablet = isTablet
        when (isTablet) {
            true -> movieDetailData = MutableLiveData()
            else -> detailClickEvent = MutableLiveData()
        }

    }

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
                onRetrieveError(it)
            })
    }

    override fun onItemClick(view: View, item: MovieLayoutModel) {
        when (isTablet) {
            true -> movieDetailData.postValue(item)
            else -> detailClickEvent.postValue(item)
        }

    }

    override fun onBottomReached(position: Int, item: MovieLayoutModel) {
        if (movieClusterList[item.type.order].pagenumber < movieClusterList[item.type.order].totalPages) {
            movieClusterList[item.type.order].pagenumber++
            fetchMovies(item.type)
        }
    }

}