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
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.activity.viewModels
import com.example.appmovies.features.home.presentation.viewmodel.DetailViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovies.features.home.presentation.adapter.ActorAdapter
import com.google.android.material.chip.Chip
import android.util.TypedValue
import androidx.core.content.ContextCompat
import com.example.appmovies.R

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

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

        // Setup Cast RecyclerView
        binding.recyclerViewCast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Fetch additional details
        viewModel.fetchDetails(movie.id)
        
        lifecycleScope.launch {
            viewModel.movieDetails.collect { details ->
                if (details != null) {
                    if (!details.overview.isNullOrEmpty()) {
                        binding.textStoryline.text = details.overview
                    } else {
                        binding.textStoryline.text = "No storyline available."
                    }
                    
                    // Populate Genres
                    binding.chipGroupGenres.removeAllViews()
                    details.genres?.forEach { genre ->
                        val chip = Chip(this@DetailActivity)
                        chip.text = genre.name
                        chip.setTextColor(ContextCompat.getColor(this@DetailActivity, R.color.white))
                        chip.setChipBackgroundColorResource(R.color.text_secondary) // Using text_secondary or a semi-transparent color for background
                        chip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
                        chip.isCheckable = false
                        binding.chipGroupGenres.addView(chip)
                    }
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.credits.collect { credits ->
                if (credits != null) {
                    val actors = credits.cast.take(10).map { it.toActor() }
                    binding.recyclerViewCast.adapter = ActorAdapter(actors) {}
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.reviews.collect { reviews ->
                if (reviews != null) {
                    // TODO: Update UI with reviews.results if needed
                }
            }
        }
    }
}
