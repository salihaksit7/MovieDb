package com.salihaksit.moviedb

import com.salihaksit.moviedb.base.BaseActivity
import com.salihaksit.moviedb.databinding.ActivityMainBinding
import com.salihaksit.moviedb.ui.MainVM

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModelClass: Class<MainVM>
        get() = MainVM::class.java

    override fun init() {

    }

}
