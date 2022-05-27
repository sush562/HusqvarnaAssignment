package com.husqvarna.dsassignment.framework.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.husqvarna.dsassignment.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

fun initRetrofitClient(context: Context): BaseApi {
    val baseUrl = context.getString(R.string.base_url)

    val gson = GsonBuilder().setLenient().create()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build().create()
}