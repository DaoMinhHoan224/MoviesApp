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

class SoonMovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<SoonMovieAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagePoster: ImageView = view.findViewById(R.id.imagePoster)
        val textTitle: TextView = view.findViewById(R.id.textTitle)
        val textSubtitle: TextView = view.findViewById(R.id.textSubtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_soon_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.textTitle.text = movie.title
        holder.textSubtitle.text = movie.subtitle

        Glide.with(holder.imagePoster.context)
            .load(movie.imageUrl)
            .transform(CircleCrop())
            .into(holder.imagePoster)
    }

    override fun getItemCount() = movies.size
}
