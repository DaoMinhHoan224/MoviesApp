package com.example.appmovies.features.home.presentation.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.appmovies.databinding.ActivityDetailBinding
import com.example.moviesapp.Movie

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Make status bar transparent
        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, 0, 0, systemBars.bottom)
            insets
        }

        val movie = intent.getParcelableExtra<Movie>("movie")
        movie?.let { setupMovieData(it) }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupMovieData(movie: Movie) {
        binding.textMovieTitle.text = movie.title
        binding.textMovieSubtitle.text = movie.subtitle
        
        Glide.with(this)
            .load(movie.imageUrl)
            .into(binding.imageMovieBackdrop)

        // Placeholder for storyline as it's not in the Movie model yet
        binding.textStoryline.text = "Miles Morales returns for the next chapter of the Oscar-winning Spider-Verse saga, an epic adventure that will transport Brooklyn’s full-time, friendly neighborhood Spider-Man across the Multiverse to join forces with Gwen Stacy and a new team of Spider-People."
    }
}
