package com.example.movieviewer.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieviewer.R
import com.example.movieviewer.adapter.MovieAdapter
import com.example.movieviewer.model.Results
import com.example.movieviewer.viewmodel.MovieListFragmentViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*


/**
 * This class used to show list of movies
 * ViewModel holds data which is got from repository
 * Observers are set to observe data, progress as well as error
 */

class MoveListFragment : Fragment() {

    private lateinit var listMovieFragmentViewModel: MovieListFragmentViewModel
    private val listMovieAdapter = MovieAdapter(arrayListOf())

    private val animalDataObserver = Observer<List<Results>> { list ->
        list?.let {
            rvMovies.visibility = View.VISIBLE
            listMovieAdapter.updateList(it)
        }

    }

    private val loadingObserver = Observer<Boolean> { isLoading ->
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        if (isLoading) {
            rvMovies.visibility = View.GONE
            tvError.visibility = View.GONE
        }
    }

    private val errorObserver = Observer<Boolean> { isError ->
        tvError.visibility = if (isError) View.VISIBLE else View.GONE
        if (isError) {
            rvMovies.visibility = View.GONE
            progressBar.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listMovieFragmentViewModel = ViewModelProviders.of(this).get(MovieListFragmentViewModel::class.java)
        listMovieFragmentViewModel.movies.observe(this, animalDataObserver)
        listMovieFragmentViewModel.loading.observe(this, loadingObserver)
        listMovieFragmentViewModel.loadError.observe(this, errorObserver)
        listMovieFragmentViewModel.refresh()
        rvMovies.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = listMovieAdapter
        }

        refreshLayout.setOnRefreshListener {
            tvError.visibility = View.GONE
            rvMovies.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            refreshLayout.isRefreshing = false
            listMovieFragmentViewModel.refresh()
        }
    }
}
