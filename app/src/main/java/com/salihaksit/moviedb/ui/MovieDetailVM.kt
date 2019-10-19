package com.salihaksit.moviedb.ui

import androidx.lifecycle.MutableLiveData
import com.salihaksit.moviedb.base.BaseViewModel
import com.salihaksit.moviedb.data.layoutmodel.MovieLayoutModel
import javax.inject.Inject

class MovieDetailVM @Inject constructor() : BaseViewModel() {
    val movieDetailData = MutableLiveData<MovieLayoutModel>()
}