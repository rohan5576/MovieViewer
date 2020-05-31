package com.example.movieviewer.view.di

import com.example.movieviewer.di.MovieApiModule
import com.example.movieviewer.viewmodel.MovieListFragmentViewModel
import dagger.Component


@Component(modules = [MovieApiModule::class])
interface ViewModelComponent {
    fun inject(viewModel: MovieListFragmentViewModel)
}