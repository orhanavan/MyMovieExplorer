package com.orhanavan.mymovieexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanavan.mymovieexplorer.data.request.PopularRequest
import com.orhanavan.mymovieexplorer.domain.NetworkImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkImplementation: NetworkImplementation
) : ViewModel() {

    fun loadPopular() {
        viewModelScope.launch {
            doPopularRequest()
        }
    }

    private suspend fun doPopularRequest() = withContext(Dispatchers.IO) {
        val list = networkImplementation.getPopular(PopularRequest())
        return@withContext
    }
}