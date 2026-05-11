package com.example.appmovies.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmovies.R
import com.example.moviesapp.Actor
import com.google.android.material.imageview.ShapeableImageView

class ActorAdapter(
    private val actors: List<Actor>,
    private val onItemClick: (Actor) -> Unit
) : RecyclerView.Adapter<ActorAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageActor: ShapeableImageView = view.findViewById(R.id.imageActor)
        val textActorName: TextView = view.findViewById(R.id.textActorName)
        val textMovieCount: TextView = view.findViewById(R.id.textMovieCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = actors[position]
        holder.textActorName.text = actor.name
        holder.textMovieCount.text = "Movies: ${actor.movieCount}"

        Glide.with(holder.imageActor.context)
            .load(actor.imageUrl)
            .centerCrop()
            .into(holder.imageActor)

        holder.itemView.setOnClickListener {
            onItemClick(actor)
        }
    }

    override fun getItemCount() = actors.size
}
