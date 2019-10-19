package com.salihaksit.moviedb.utils

interface OnBottomReachedListener<T> {
    fun onBottomReached(position: Int, item : T)
}