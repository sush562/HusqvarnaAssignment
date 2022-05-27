package com.husqvarna.dsassignment.framework.di

import com.husqvarna.dsassignment.data.datasources.MovieDataSource
import com.husqvarna.dsassignment.data.repository.impl.MovieRepositoryImpl
import com.husqvarna.dsassignment.domain.repository.MovieRepository
import com.husqvarna.dsassignment.domain.usecase.FetchMovieDetailUseCase
import com.husqvarna.dsassignment.domain.usecase.FetchPopularMoviesUseCase
import com.husqvarna.dsassignment.framework.datasource.impl.MovieDataSourceImpl
import com.husqvarna.dsassignment.framework.network.BaseApi
import com.husqvarna.dsassignment.framework.network.initRetrofitClient
import com.husqvarna.dsassignment.utils.Utils
import com.husqvarna.dsassignment.view.viewmodel.MovieDetailViewModel
import com.husqvarna.dsassignment.view.viewmodel.MovieSharedViewModel
import com.husqvarna.dsassignment.view.viewmodel.MovieViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val frameWorkModules = module {
    single<BaseApi> { initRetrofitClient(context = androidContext()) }
    single { Utils(context = androidApplication()) }
}

val vmModules = module {
    factory { MovieViewModel(fetchPopularMoviesUseCase = get()) }
    factory { MovieDetailViewModel(fetchMovieDetailUseCase = get()) }
    factory { MovieSharedViewModel() }
}

val domainModules = module {
    factory { FetchPopularMoviesUseCase(movieRepository = get()) }
    factory { FetchMovieDetailUseCase(movieRepository = get()) }

    factory<MovieRepository> { MovieRepositoryImpl(movieDataSource = get()) }
}

val dataModules = module {
    factory<MovieDataSource> { MovieDataSourceImpl(baseApi = get(), context = androidContext(), utils = get()) }
}