package com.orhanavan.mymovieexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.orhanavan.mymovieexplorer.domain.implementation.NetworkImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkImplementation: NetworkImplementation
) : ViewModel() {


}