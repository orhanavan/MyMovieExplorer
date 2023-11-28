package com.orhanavan.mymovieexplorer.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

fun Double.formatRating(): String {
    return "%.1f".format(this)
}

fun Double.formatStar(): Float {
    return (this / 2).toFloat()
}

fun String.formatDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())

    return try {
        val date = inputFormat.parse(this)
        outputFormat.format(date!!)
    } catch (e: ParseException) {
        this
    }
}