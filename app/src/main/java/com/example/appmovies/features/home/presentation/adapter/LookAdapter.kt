package com.example.appmovies.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmovies.R
import com.example.moviesapp.Movie

class LookAdapter(
    private val movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<LookAdapter.LookViewHolder>() {

    class LookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageLookPoster: ImageView = itemView.findViewById(R.id.imageLookPoster)
        val tvLookTitle: TextView = itemView.findViewById(R.id.tvLookTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_look, parent, false)
        return LookViewHolder(view)
    }

    override fun onBindViewHolder(holder: LookViewHolder, position: Int) {
        val movie = movies[position]
        holder.tvLookTitle.text = movie.title
        Glide.with(holder.itemView.context)
            .load(movie.imageUrl)
            .into(holder.imageLookPoster)
            
        holder.itemView.setOnClickListener {
            onItemClick(movie)
        }
    }

    override fun getItemCount(): Int = movies.size
}
