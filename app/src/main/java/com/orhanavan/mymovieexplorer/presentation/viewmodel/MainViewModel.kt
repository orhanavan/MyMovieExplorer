package com.orhanavan.mymovieexplorer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanavan.mymovieexplorer.data.model.Genre
import com.orhanavan.mymovieexplorer.data.request.PopularRequest
import com.orhanavan.mymovieexplorer.data.model.Movie
import com.orhanavan.mymovieexplorer.data.request.GenreRequest
import com.orhanavan.mymovieexplorer.domain.implementation.NetworkImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkImplementation: NetworkImplementation
) : ViewModel() {

    private val _popularList = MutableLiveData<List<Movie>>()
    val popularList: LiveData<List<Movie>> get() = _popularList

    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>> get() = _genreList


    init {
        viewModelScope.launch {
            runBlocking {
                doGenreRequest()
                doPopularRequest()
            }
        }
    }

    private suspend fun doGenreRequest() = withContext(Dispatchers.IO) {
        val response = networkImplementation.getGenre(GenreRequest())
        _genreList.postValue(response.genres)
        return@withContext
    }

    private suspend fun doPopularRequest() = withContext(Dispatchers.IO) {
        val response = networkImplementation.getPopular(PopularRequest())
        _popularList.postValue(response.results)
        return@withContext
    }
}