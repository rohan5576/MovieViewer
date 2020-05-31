package com.example.movieviewer.di

import com.example.movieviewer.view.service.MoviesService
import dagger.Component



@Component(modules = [MovieApiModule::class])
interface MovieApiCompoenent {

    //  injecting i.e. in MoviesService
    fun injectService(service: MoviesService)
}