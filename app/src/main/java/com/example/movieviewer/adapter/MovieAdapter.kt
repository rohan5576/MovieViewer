package com.example.movieviewer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.movieviewer.model.Results
import com.example.movieviewer.util.OnMovieClick
import com.example.movieviewer.R
import com.example.movieviewer.databinding.MovieItemLayoutBinding
import com.example.movieviewer.view.fragment.MoveListFragmentDirections

/**
 * MovieAdapter class.
 */

class MovieAdapter(private val moviesList: ArrayList<Results>) :
    RecyclerView.Adapter<MovieAdapter.MovieItemViewHolder>(), OnMovieClick {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.movie_item_layout,
                parent, false
            )
        )
    }

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val result = moviesList[position]
        holder.view.movie = result
        holder.view.onClick = this
    }

    fun updateList(newAnimalList: List<Results>) {
        moviesList.clear()
        moviesList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

    override fun onClick(view: View) {
        for (movie: Results in moviesList) {
            if (view.tag == movie.title) {
                val action =
                    MoveListFragmentDirections.actionGoToDetails(movie)
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

    class MovieItemViewHolder(var view: MovieItemLayoutBinding) : RecyclerView.ViewHolder(view.root)
}