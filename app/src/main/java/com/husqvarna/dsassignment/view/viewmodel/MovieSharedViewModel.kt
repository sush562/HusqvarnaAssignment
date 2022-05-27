package com.husqvarna.dsassignment.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.husqvarna.dsassignment.data.entity.MovieDetailData

class MovieSharedViewModel: ViewModel() {

    private val _openDetailPage: MutableLiveData<MovieDetailData> = MutableLiveData()
    val openDetailPage: LiveData<MovieDetailData> get() = _openDetailPage

    private val _showError: MutableLiveData<Unit> = MutableLiveData()
    val showError: LiveData<Unit> get() = _showError

    private val _toolbarTitle: MutableLiveData<String> = MutableLiveData()
    val toolbarTitle: LiveData<String> get() = _toolbarTitle

    private val _fetchingData: MutableLiveData<Boolean> = MutableLiveData()
    val fetchingData: LiveData<Boolean> get() = _fetchingData

    fun openMovieDetailPage(detail: MovieDetailData) {
        _openDetailPage.value = detail
    }

    fun showError() {
        _showError.value = Unit
    }

    fun isFetchingData(isFetch: Boolean) {
        _fetchingData.value = isFetch
    }

    fun setToolbarTitle(title: String) {
        _toolbarTitle.value = title
    }
}