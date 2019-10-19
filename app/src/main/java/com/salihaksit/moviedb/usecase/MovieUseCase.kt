package com.salihaksit.moviedb.usecase

import com.salihaksit.moviedb.base.adapter.ItemClickListener
import com.salihaksit.moviedb.data.MovieListType
import com.salihaksit.moviedb.data.repository.MovieRepository
import com.salihaksit.moviedb.data.layoutmodel.MovieClusterLayoutModel
import com.salihaksit.moviedb.data.viewdata.MovieListViewData
import com.salihaksit.moviedb.data.layoutmodel.MovieLayoutModel
import com.salihaksit.moviedb.utils.OnBottomReachedListener
import io.reactivex.Observable
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    fun getMovieList(page: Int, type: MovieListType): Observable<MovieListViewData> {
        // todo use base image url
        return movieRepository.getMovies(page, type.sortType)
            .map {
                MovieListViewData(
                    it.page,
                    it.total_pages,
                    it.results.map {
                        MovieLayoutModel(
                            "https://image.tmdb.org/t/p/w500" + it.poster_path,
                            type
                        )
                    }.toMutableList()
                )
            }
    }

    fun getMovieClusterList(
        listener: ItemClickListener<MovieLayoutModel>,
        onBottomReachedListener: OnBottomReachedListener<MovieLayoutModel>
    ): MutableList<MovieClusterLayoutModel> {
        return movieRepository.getMovieClusterList(listener, onBottomReachedListener)
    }
}