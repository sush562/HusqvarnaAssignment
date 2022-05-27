package com.husqvarna.dsassignment.domain.repository

import com.husqvarna.dsassignment.data.entity.TMDBMovie
import com.husqvarna.dsassignment.data.entity.TMDBMovieDetail

interface MovieRepository {

    suspend fun fetchPopularMovieList(): List<TMDBMovie>

    suspend fun fetchMovieDetail(id: Int): TMDBMovieDetail?
}