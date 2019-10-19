package com.salihaksit.moviedb.base

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: VM

    protected abstract val layoutResourceId: Int

    protected lateinit var dataBinding: B

    protected abstract fun init()

    abstract val viewModelClass: Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater, layoutResourceId, container, false
        )
        dataBinding.lifecycleOwner = this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isAdded) {
            Log.d("Fragment", "$javaClass Fragment is added .")
            init()
        } else {
            Log.d("Fragment", "$javaClass Fragment is not added.")
        }
    }
}