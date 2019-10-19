package com.salihaksit.moviedb.base.adapter

import android.view.View

interface ItemClickListener<T> {

    fun onItemClick(view: View, item: T)

}