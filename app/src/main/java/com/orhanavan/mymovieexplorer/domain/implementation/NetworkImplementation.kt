package com.orhanavan.mymovieexplorer.domain.implementation

import com.orhanavan.mymovieexplorer.data.NetworkApi
import com.orhanavan.mymovieexplorer.data.request.GenreRequest
import com.orhanavan.mymovieexplorer.data.request.PopularRequest
import com.orhanavan.mymovieexplorer.data.response.GenreResponse
import com.orhanavan.mymovieexplorer.data.response.PopularResponse
import com.orhanavan.mymovieexplorer.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkImplementation @Inject constructor(
    private val networkApi: NetworkApi
) : NetworkRepository {
    override suspend fun getPopular(request: PopularRequest): PopularResponse = networkApi.getPopular()
    override suspend fun getGenre(request: GenreRequest): GenreResponse = networkApi.getGenre()

}