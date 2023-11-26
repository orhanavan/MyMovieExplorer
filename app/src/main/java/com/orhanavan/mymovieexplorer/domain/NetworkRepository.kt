package com.orhanavan.mymovieexplorer.domain

import com.orhanavan.mymovieexplorer.data.request.PopularRequest
import com.orhanavan.mymovieexplorer.data.response.PopularResponse

interface NetworkRepository {
    suspend fun getPopular(request: PopularRequest): PopularResponse
}