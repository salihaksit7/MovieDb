package com.salihaksit.moviedb.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    protected lateinit var viewModel: VM
    protected lateinit var dataBinding: B

    protected abstract val layoutResourceId: Int

    abstract val viewModelClass: Class<VM>

    protected abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        bindView(layoutResourceId)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        init()
    }


    private fun bindView(layoutId: Int) {
        dataBinding = DataBindingUtil.setContentView(this, layoutId)
        dataBinding.lifecycleOwner = this
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}