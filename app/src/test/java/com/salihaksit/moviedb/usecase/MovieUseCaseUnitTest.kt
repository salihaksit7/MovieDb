package com.salihaksit.moviedb.usecase

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.salihaksit.moviedb.base.adapter.ItemClickListener
import com.salihaksit.moviedb.data.MovieListType
import com.salihaksit.moviedb.data.layoutmodel.MovieLayoutModel
import com.salihaksit.moviedb.data.repository.MovieRepository
import com.salihaksit.moviedb.network.Api
import com.salihaksit.moviedb.network.models.MovieResponseModel
import com.salihaksit.moviedb.utils.OnBottomReachedListener
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class MovieUseCaseUnitTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val api = mock(Api::class.java)

    val movieRepository by lazy { MovieRepository(api) }
    val movieUseCase by lazy { MovieUseCase(movieRepository) }

    @Test
    fun testMovieUseCase_getMovieList_Completed() {
        Mockito.`when`(movieRepository.getMovies(1, MovieListType.Popular.sortType))
            .thenReturn(Observable.just(MovieResponseModel(0, arrayListOf(), 0, 0)))

        movieUseCase.getMovieList(1, MovieListType.Popular)
            .test()
            .assertComplete()
    }

    @Test
    fun testMovieUseCase_getMovieClusterListCount_matchWithMovieListTypeCount() {

        val movieClusterListCount = movieUseCase.getMovieClusterList(object :
            ItemClickListener<MovieLayoutModel> {
            override fun onItemClick(view: View, item: MovieLayoutModel) {

            }

        }, object : OnBottomReachedListener<MovieLayoutModel> {
            override fun onBottomReached(position: Int, item: MovieLayoutModel) {

            }

        }).size

        assertEquals(movieClusterListCount, MovieListType.values().size)
    }
}
