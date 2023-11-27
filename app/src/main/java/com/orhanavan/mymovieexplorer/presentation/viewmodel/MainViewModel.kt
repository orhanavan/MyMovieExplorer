package com.orhanavan.mymovieexplorer.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanavan.mymovieexplorer.data.request.PopularRequest
import com.orhanavan.mymovieexplorer.data.response.Popular
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

    private val _popularList = MutableLiveData<List<Popular>>()
    val popularList = _popularList

    init {
        viewModelScope.launch {
            doPopularRequest()
        }
    }

    private suspend fun doPopularRequest() = withContext(Dispatchers.IO) {
        val response = networkImplementation.getPopular(PopularRequest())
        _popularList.postValue(response.results)
        return@withContext
    }
}