package com.salihaksit.moviedb.ui.bindingadapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.salihaksit.moviedb.utils.getCircularProgressDrawable


@BindingAdapter("imageUrl")
fun loadImageUrl(
    view: AppCompatImageView, url: String?
) {
    Glide.with(view.context)
        .load(url ?: "")
        .placeholder(view.context.getCircularProgressDrawable())
        .into(view)
}