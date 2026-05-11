package com.example.appmovies.features.home.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmovies.R
import com.example.appmovies.features.home.presentation.adapter.SoonMovieAdapter
import com.example.moviesapp.Movie

class HomeFragment : Fragment() {

    private val featuredMovie = Movie(
        2,
        "John Wick: Chapter 4",
        "Released this Week",
        "https://image.tmdb.org/t/p/original/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupRecyclerView(view)
    }

    private fun setupViews(view: View) {
        val imageFeatured = view.findViewById<ImageView>(R.id.imageFeatured)
        Glide.with(this)
            .load(featuredMovie.imageUrl)
            .into(imageFeatured)

        imageFeatured.setOnClickListener {
            navigateToDetail(featuredMovie)
        }

        val imageAvatar = view.findViewById<ImageView>(R.id.imageAvatar)
        Glide.with(this)
            .load("https://i.pravatar.cc/150?u=linh")
            .circleCrop()
            .into(imageAvatar)
    }

    private fun setupRecyclerView(view: View) {
        // Soon Movies
        val recyclerViewSoon = view.findViewById<RecyclerView>(R.id.recyclerViewSoon)
        recyclerViewSoon.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val soonMovies = listOf(
            Movie(1, "Into the Spider-Verse 2", "Released this Week", "https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg"),
            Movie(2, "John Wick: Chapter 4", "Released this Week", "https://image.tmdb.org/t/p/w500/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg"),
            Movie(3, "Thor: Love and Thunder", "Released this Week", "https://image.tmdb.org/t/p/w500/pIkRyD18kl4FhoCNQuWxWu5c1El.jpg")
        )
        recyclerViewSoon.adapter = SoonMovieAdapter(soonMovies) { movie ->
            navigateToDetail(movie)
        }

        // Popular Movies
        val recyclerViewPopular = view.findViewById<RecyclerView>(R.id.recyclerViewPopular)
        recyclerViewPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val popularMovies = listOf(
            Movie(4, "Avatar: The Way of Water", "Top Popular", "https://image.tmdb.org/t/p/w500/t6HIqrRAclMCA60NsSmeqe9RmY1.jpg"),
            Movie(5, "Puss in Boots: The Last Wish", "Top Popular", "https://image.tmdb.org/t/p/w500/kuf6Yak7IyHpxkoLQU3SnuHdnrM.jpg"),
            Movie(6, "The Super Mario Bros. Movie", "Top Popular", "https://image.tmdb.org/t/p/w500/qNBAXBIQlnOzb6Uhw68B793S3oq.jpg")
        )
        recyclerViewPopular.adapter = SoonMovieAdapter(popularMovies) { movie ->
            navigateToDetail(movie)
        }
    }

    private fun navigateToDetail(movie: Movie) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}
