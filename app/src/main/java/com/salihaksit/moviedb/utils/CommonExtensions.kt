package com.salihaksit.moviedb.utils

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun <T> LifecycleOwner.observe(liveData: LiveData<T?>, lambda: (T) -> Unit) {
    liveData.observe(this, Observer { if (it != null) lambda(it) })
}

fun Context.getCircularProgressDrawable(): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(this)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    return circularProgressDrawable
}