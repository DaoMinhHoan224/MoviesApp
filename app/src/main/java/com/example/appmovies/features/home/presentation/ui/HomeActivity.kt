package com.example.appmovies.features.home.presentation.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovies.R
import com.example.appmovies.features.home.presentation.adapter.SoonMovieAdapter
import com.example.moviesapp.Movie
import com.bumptech.glide.Glide

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        // Make status bar transparent
        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, systemBars.bottom)
            insets
        }

        setupViews()
        setupRecyclerView()
    }

    private fun setupViews() {
        val imageFeatured = findViewById<ImageView>(R.id.imageFeatured)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg")
            .into(imageFeatured)

        val imageAvatar = findViewById<ImageView>(R.id.imageAvatar)
        Glide.with(this)
            .load("https://i.pravatar.cc/150?u=linh")
            .circleCrop()
            .into(imageAvatar)
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSoon)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val movies = listOf(
            Movie(
                1,
                "Into the Spider-Verse 2",
                "Released this Week",
                "https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg"
            ),
            Movie(2, "John Wick: Chapter 4", "Released this Week", "https://image.tmdb.org/t/p/w500/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg"),
            Movie(3, "Thor: Love and Thunder", "Released this Week", "https://image.tmdb.org/t/p/w500/pIkRyD18kl4FhoCNQuWxWu5c1El.jpg")
        )
        recyclerView.adapter = SoonMovieAdapter(movies)
    }
}