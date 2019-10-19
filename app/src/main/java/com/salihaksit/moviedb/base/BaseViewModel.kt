package com.salihaksit.moviedb.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    lateinit var disposable: Disposable

    var progressVisibility: MutableLiveData<Boolean> = MutableLiveData()

    fun onRetrieveFinish() {
        progressVisibility.value = false
    }

    fun onRetrieveStart() {
        progressVisibility.value = true
    }


    open fun onRetrieveError(error: Throwable?) {
        Log.d("DisposableError", error.toString())
    }

    override fun onCleared() {
        if (::disposable.isInitialized)
            disposable.dispose()
        super.onCleared()
    }

}