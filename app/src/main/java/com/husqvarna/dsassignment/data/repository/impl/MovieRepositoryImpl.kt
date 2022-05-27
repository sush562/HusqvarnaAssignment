package com.husqvarna.dsassignment.data.repository.impl

import com.husqvarna.dsassignment.data.datasources.MovieDataSource
import com.husqvarna.dsassignment.data.entity.TMDBMovie
import com.husqvarna.dsassignment.data.entity.TMDBMovieDetail
import com.husqvarna.dsassignment.domain.repository.MovieRepository

class MovieRepositoryImpl(private val movieDataSource: MovieDataSource): MovieRepository {

    override suspend fun fetchPopularMovieList(): List<TMDBMovie> = movieDataSource.fetchPopularMovieList()

    override suspend fun fetchMovieDetail(id: Int): TMDBMovieDetail? = movieDataSource.fetchMovieDetail(id)
}