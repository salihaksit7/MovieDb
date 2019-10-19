package com.salihaksit.moviedb.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun Context.showAlertDialog(
    dialogBuilder: AlertDialog.Builder.() -> Any
) {
    val builder = AlertDialog.Builder(this)
    builder.dialogBuilder()
    val dialog = builder.create()
    dialog.show()
}

fun AlertDialog.Builder.positiveButton(
    text: String = "YES",
    handleClick: (which: Int) -> Unit = {}
) {
    this.setPositiveButton(text) { _, which -> handleClick(which) }
}

fun AlertDialog.Builder.negativeButton(
    text: String = "NO",
    handleClick: (which: Int) -> Unit = {}
) {
    this.setNegativeButton(text) { _, which -> handleClick(which) }
}