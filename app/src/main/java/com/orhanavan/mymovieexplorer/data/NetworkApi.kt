package com.orhanavan.mymovieexplorer.data

import com.orhanavan.mymovieexplorer.data.response.GenreResponse
import com.orhanavan.mymovieexplorer.data.response.PopularResponse
import retrofit2.http.GET

interface NetworkApi {

    @GET("movie/popular")
    suspend fun getPopular() : PopularResponse

    @GET("genre/movie/list")
    suspend fun getGenre() : GenreResponse
}