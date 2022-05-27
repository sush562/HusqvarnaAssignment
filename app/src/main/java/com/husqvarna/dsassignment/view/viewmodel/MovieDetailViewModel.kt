package com.husqvarna.dsassignment.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.husqvarna.dsassignment.data.entity.TMDBMovieDetail
import com.husqvarna.dsassignment.domain.usecase.FetchMovieDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val fetchMovieDetailUseCase: FetchMovieDetailUseCase): ViewModel() {

    var movieId: Int = 0
    var backdropPath = ""
    var movieTitle = ""

    private val _movieDetail: MutableLiveData<TMDBMovieDetail?> = MutableLiveData()
    val movieDetail: LiveData<TMDBMovieDetail?> get() = _movieDetail

    fun fetchMovieDetail() {
        viewModelScope.launch(Dispatchers.Main) {
            delay(500)
            _movieDetail.value = fetchMovieDetailUseCase(movieId)
        }
    }
}