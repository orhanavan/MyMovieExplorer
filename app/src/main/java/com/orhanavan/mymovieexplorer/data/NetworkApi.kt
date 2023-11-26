package com.orhanavan.mymovieexplorer.data

import com.orhanavan.mymovieexplorer.data.response.PopularResponse
import retrofit2.http.GET

interface NetworkApi {

    @GET("movie/popular")
    suspend fun getPopular() : PopularResponse
}