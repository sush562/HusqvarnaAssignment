package com.husqvarna.dsassignment.data.entity

import com.google.gson.annotations.SerializedName

data class TMDBMovieResponse(val page: Int, val results: List<TMDBMovie>, @SerializedName("total_pages") val totalPages: Int)

data class TMDBMovie(
    val id: Int,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("poster_path") var posterPath: String
)


data class TMDBMovieDetail(
    val id: Int,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    val runtime: Int,
    val overview: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)

data class MovieDetailData(val id: Int, val backdropPath: String, val movieTitle: String)