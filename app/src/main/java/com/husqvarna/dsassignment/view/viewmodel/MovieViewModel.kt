package com.husqvarna.dsassignment.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.husqvarna.dsassignment.data.entity.TMDBMovie
import com.husqvarna.dsassignment.domain.usecase.FetchPopularMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieViewModel(private val fetchPopularMoviesUseCase: FetchPopularMoviesUseCase): ViewModel() {

    private val _movieList: MutableLiveData<List<TMDBMovie>> = MutableLiveData()
    val movieList: LiveData<List<TMDBMovie>> get() = _movieList

    fun fetchPopularMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            delay(500)
            _movieList.value = fetchPopularMoviesUseCase()
        }
    }
}