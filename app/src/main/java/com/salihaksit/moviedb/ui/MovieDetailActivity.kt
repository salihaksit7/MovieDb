package com.salihaksit.moviedb.ui

import com.salihaksit.moviedb.R
import com.salihaksit.moviedb.base.BaseActivity
import com.salihaksit.moviedb.data.layoutmodel.MovieLayoutModel
import com.salihaksit.moviedb.databinding.ActivityMovieDetailBinding
import com.salihaksit.moviedb.viewmodels.MovieDetailVM

class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding, MovieDetailVM>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_movie_detail
    override val viewModelClass: Class<MovieDetailVM>
        get() = MovieDetailVM::class.java

    override fun init() {
        dataBinding.viewModel = viewModel
        intent.getSerializableExtra("movieDetail")?.let {
            viewModel.movieDetailData.postValue(it as MovieLayoutModel)
        }
    }

}