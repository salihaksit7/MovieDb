package com.salihaksit.moviedb.ui

import android.content.Intent
import androidx.lifecycle.Observer
import com.salihaksit.moviedb.R
import com.salihaksit.moviedb.base.BaseActivity
import com.salihaksit.moviedb.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModelClass: Class<MainVM>
        get() = MainVM::class.java

    override fun init() {
        dataBinding.viewModel = viewModel
        viewModel.getMovies()
        viewModel.detailClickEvent.observe(this, Observer {
            it?.let {
                startActivity(
                    Intent(this, MovieDetailActivity::class.java).putExtra(
                        "movieDetail",
                        it
                    )
                )
            }
        })
    }

}
