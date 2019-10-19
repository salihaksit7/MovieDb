package com.salihaksit.moviedb.ui

import android.content.Intent
import androidx.lifecycle.Observer
import com.salihaksit.moviedb.R
import com.salihaksit.moviedb.base.BaseActivity
import com.salihaksit.moviedb.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModelClass: Class<MainVM>
        get() = MainVM::class.java

    override fun init() {
        val isTablet = scroll_view != null
        dataBinding.viewModel = viewModel
        viewModel.setTablet(isTablet)
        viewModel.getMovies()
        if (isTablet)
            return

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
