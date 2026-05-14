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
import androidx.lifecycle.lifecycleScope
import com.example.appmovies.core.network.RetrofitClient
import kotlinx.coroutines.launch
import com.example.appmovies.features.home.presentation.adapter.SoonMovieAdapter
import com.example.moviesapp.Movie

import androidx.fragment.app.viewModels
import com.example.appmovies.features.home.presentation.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

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
        observeViewModel(view)
    }

    private fun setupViews(view: View) {
        val imageAvatar = view.findViewById<ImageView>(R.id.imageAvatar)
        Glide.with(this)
            .load("https://i.pravatar.cc/150?u=linh")
            .circleCrop()
            .into(imageAvatar)
    }

    private fun observeViewModel(view: View) {
        val recyclerViewSoon = view.findViewById<RecyclerView>(R.id.recyclerViewSoon)
        recyclerViewSoon.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        
        val recyclerViewPopular = view.findViewById<RecyclerView>(R.id.recyclerViewPopular)
        recyclerViewPopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        
        val imageFeatured = view.findViewById<ImageView>(R.id.imageFeatured)

        lifecycleScope.launch {
            viewModel.nowPlayingMovies.collect { movies ->
                recyclerViewSoon.adapter = SoonMovieAdapter(movies) { navigateToDetail(it) }
                
                if (movies.isNotEmpty()) {
                    val featured = movies.first()
                    Glide.with(this@HomeFragment).load(featured.imageUrl).into(imageFeatured)
                    imageFeatured.setOnClickListener { navigateToDetail(featured) }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.popularMovies.collect { movies ->
                recyclerViewPopular.adapter = SoonMovieAdapter(movies) { navigateToDetail(it) }
            }
        }
    }

    private fun navigateToDetail(movie: Movie) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}
