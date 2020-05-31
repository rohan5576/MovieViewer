package com.example.movieviewer.view.service

import com.example.movieviewer.di.DaggerMovieApiCompoenent
import javax.inject.Inject

class MoviesService {

    @Inject
    lateinit var retrofit: MoviesAPIs

    init {
        DaggerMovieApiCompoenent.create().injectService(this)
    }

    fun getPopularMovies(apiKey: String) = retrofit.getPopularMovies(apiKey)
}