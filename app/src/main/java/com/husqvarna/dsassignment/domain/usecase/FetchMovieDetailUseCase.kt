package com.husqvarna.dsassignment.domain.usecase

import com.husqvarna.dsassignment.data.entity.TMDBMovieDetail
import com.husqvarna.dsassignment.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchMovieDetailUseCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(id: Int): TMDBMovieDetail? = withContext(Dispatchers.Default) {
        return@withContext if (id > 0) movieRepository.fetchMovieDetail(id) else null
    }
}