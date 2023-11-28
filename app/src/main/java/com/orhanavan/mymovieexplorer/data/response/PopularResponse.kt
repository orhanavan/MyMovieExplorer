package com.orhanavan.mymovieexplorer.data.response

import com.orhanavan.mymovieexplorer.data.model.Movie

data class PopularResponse(
    val page: String,
    val results: List<Movie>
)