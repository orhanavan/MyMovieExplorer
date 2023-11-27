package com.orhanavan.mymovieexplorer.util

fun Double.formatRating(): String {
    return "%.1f".format(this)
}

fun Double.formatStar(): Float {
    return (this / 2).toFloat()
}