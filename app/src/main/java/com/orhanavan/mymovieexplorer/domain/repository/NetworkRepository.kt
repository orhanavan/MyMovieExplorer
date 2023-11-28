package com.orhanavan.mymovieexplorer.domain.repository

import com.orhanavan.mymovieexplorer.data.request.GenreRequest
import com.orhanavan.mymovieexplorer.data.request.PopularRequest
import com.orhanavan.mymovieexplorer.data.response.GenreResponse
import com.orhanavan.mymovieexplorer.data.response.PopularResponse

interface NetworkRepository {
    suspend fun getPopular(request: PopularRequest): PopularResponse
    suspend fun getGenre(request: GenreRequest): GenreResponse
}