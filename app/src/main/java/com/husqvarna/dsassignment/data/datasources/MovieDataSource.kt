package com.husqvarna.dsassignment.data.datasources

import com.husqvarna.dsassignment.data.entity.TMDBMovie
import com.husqvarna.dsassignment.data.entity.TMDBMovieDetail

interface MovieDataSource {

    suspend fun fetchPopularMovieList(): List<TMDBMovie>

    suspend fun fetchMovieDetail(id: Int): TMDBMovieDetail?
}