package com.salihaksit.moviedb.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.salihaksit.moviedb.network.models.BaseError
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseViewModel : ViewModel() {

    lateinit var disposable: Disposable

    val progressVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun onRetrieveFinish() {
        progressVisibility.value = false
    }

    fun onRetrieveStart() {
        progressVisibility.value = true
    }


    open fun onRetrieveError(error: Throwable) {
        Log.d("DisposableError", error.toString())
        handleError(error)
    }

    override fun onCleared() {
        if (::disposable.isInitialized)
            disposable.dispose()
        super.onCleared()
    }


    private fun handleError(error: Throwable) {
        when (error) {
            is HttpException -> {
                val baseError: BaseError?
                try {
                    baseError = Gson().fromJson<BaseError>(
                        error.response().errorBody()!!.string(),
                        BaseError::class.java
                    )

                    errorMessage.postValue(baseError?.status_message)
                } catch (e: IOException) {
                    errorMessage.value = "Unknown error"
                }

            }
            is UnknownHostException -> errorMessage.postValue("Please check your internet connection")
            is SocketTimeoutException -> errorMessage.postValue("Timeout")
            else -> errorMessage.postValue("Unknown error")
        }
    }
}