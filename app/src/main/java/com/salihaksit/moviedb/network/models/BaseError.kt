package com.salihaksit.moviedb.network.models

data class BaseError(
    val status_message: String,
    val status_code: Int
)