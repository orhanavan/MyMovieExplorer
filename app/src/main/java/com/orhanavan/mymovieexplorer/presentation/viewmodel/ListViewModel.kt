package com.orhanavan.mymovieexplorer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanavan.mymovieexplorer.data.model.Genre
import com.orhanavan.mymovieexplorer.data.model.Movie
import com.orhanavan.mymovieexplorer.data.request.GenreRequest
import com.orhanavan.mymovieexplorer.data.request.PopularRequest
import com.orhanavan.mymovieexplorer.domain.implementation.NetworkImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val networkImplementation: NetworkImplementation
) : ViewModel() {

    private val _popularList = MutableLiveData<List<Movie>>()
    val popularList: LiveData<List<Movie>> get() = _popularList

    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>> get() = _genreList

    init {
        viewModelScope.launch {
            val genreResponse = networkImplementation.getGenre(GenreRequest())
            _genreList.postValue(genreResponse.genres)
        }

        viewModelScope.launch {
            val popularResponse = networkImplementation.getPopular(PopularRequest())

            for (movie in popularResponse.results) {
                movie.genres = getGenreNamesByIds(movie.genreIds)
            }

            _popularList.postValue(popularResponse.results)
        }
    }

    private fun getGenreNamesByIds(ids: List<Int>): String {
        val selectedGenres = _genreList.value?.filter { it.id in ids }
        return selectedGenres?.joinToString(separator = ", ") { it.name } ?: "Unknown"
    }
}