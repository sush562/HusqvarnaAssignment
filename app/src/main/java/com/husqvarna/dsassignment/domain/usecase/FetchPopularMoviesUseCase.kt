package com.husqvarna.dsassignment.domain.usecase

import com.husqvarna.dsassignment.data.entity.TMDBMovie
import com.husqvarna.dsassignment.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchPopularMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(): List<TMDBMovie> = withContext(Dispatchers.Default) {
        return@withContext movieRepository.fetchPopularMovieList()
    }
}