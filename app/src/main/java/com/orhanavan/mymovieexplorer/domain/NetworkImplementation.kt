package com.orhanavan.mymovieexplorer.domain

import com.orhanavan.mymovieexplorer.data.NetworkApi
import com.orhanavan.mymovieexplorer.data.request.PopularRequest
import com.orhanavan.mymovieexplorer.data.response.PopularResponse
import javax.inject.Inject

class NetworkImplementation @Inject constructor(
    private val networkApi: NetworkApi
) : NetworkRepository {
    override suspend fun getPopular(request: PopularRequest): PopularResponse = networkApi.getPopular()

}