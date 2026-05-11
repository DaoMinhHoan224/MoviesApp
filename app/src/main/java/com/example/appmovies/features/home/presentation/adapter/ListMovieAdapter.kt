package com.example.appmovies.features.home.presentation.adapter

import com.example.moviesapp.Movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.appmovies.R

class ListMovieAdapter(
    private val movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<ListMovieAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageMoviePoster: ImageView = view.findViewById(R.id.imageMoviePoster)
        val textMovieTitle: TextView = view.findViewById(R.id.textMovieTitle)
        val textMovieSubtitle: TextView = view.findViewById(R.id.textMovieSubtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.textMovieTitle.text = movie.title
        holder.textMovieSubtitle.text = movie.subtitle

        Glide.with(holder.imageMoviePoster.context)
            .load(movie.imageUrl)
            .into(holder.imageMoviePoster)

        holder.itemView.setOnClickListener {
            onItemClick(movie)
        }
    }

    override fun getItemCount() = movies.size
}
