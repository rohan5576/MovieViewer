package com.example.movieviewer.di

import com.example.movieviewer.view.service.MoviesAPIs
import com.example.movieviewer.view.service.MoviesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This is module class injecting dependancy
 * Here we are providing api service object
 */

@Module
class MovieApiModule {
    private val BASE_URL = "https://api.themoviedb.org/"

    //Movies api provider
    @Provides
    fun provideMovieAPI(): MoviesAPIs {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MoviesAPIs::class.java)
    }

    // Service Provider
    @Provides
    fun provideService(): MoviesService {
        return MoviesService()
    }
}