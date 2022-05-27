package com.husqvarna.dsassignment.framework.datasource.impl

import android.content.Context
import android.util.Log
import com.husqvarna.dsassignment.R
import com.husqvarna.dsassignment.data.datasources.MovieDataSource
import com.husqvarna.dsassignment.data.entity.TMDBMovie
import com.husqvarna.dsassignment.data.entity.TMDBMovieDetail
import com.husqvarna.dsassignment.framework.network.BaseApi
import com.husqvarna.dsassignment.utils.Utils

class MovieDataSourceImpl(
    private val baseApi: BaseApi,
    private val context: Context,
    private val utils: Utils
) : MovieDataSource {

    override suspend fun fetchPopularMovieList(): List<TMDBMovie> {
        return if (utils.isOnline()) {
            val response = baseApi.getPopularMovieList(context.getString(R.string.tmdb_api_key))
            if (response.isSuccessful) {
                response.body()?.results ?: emptyList()
            } else {
                Log.e("MovieDataSource", response.errorBody()?.string() ?: "Error body null")
                emptyList()
            }
        } else {
            Log.e("MovieDataSource", "No network")
            emptyList()
        }
    }

    override suspend fun fetchMovieDetail(id: Int): TMDBMovieDetail? {
        return if(utils.isOnline()) {
            val response =  baseApi.getMovieDetail(id, context.getString(R.string.tmdb_api_key))
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("MovieDataSource", response.errorBody()?.string() ?: "Error body null")
                null
            }
        } else {
            Log.e("MovieDataSource", "No network")
            null
        }

    }
}