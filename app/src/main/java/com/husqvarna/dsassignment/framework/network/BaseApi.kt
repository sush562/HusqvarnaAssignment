package com.husqvarna.dsassignment.framework.network

import com.husqvarna.dsassignment.data.entity.TMDBMovie
import com.husqvarna.dsassignment.data.entity.TMDBMovieDetail
import com.husqvarna.dsassignment.data.entity.TMDBMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BaseApi {

    @GET("popular")
    suspend fun getPopularMovieList(@Query("api_key") apiKey: String): Response<TMDBMovieResponse>

    @GET("{id}")
    suspend fun getMovieDetail(@Path("id") id: Int, @Query("api_key") apiKey: String): Response<TMDBMovieDetail>
}
