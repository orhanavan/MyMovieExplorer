package com.orhanavan.mymovieexplorer.data.request

data class PopularRequest(
    val language: String = "tr-TR",
    val page: Int = 1
)