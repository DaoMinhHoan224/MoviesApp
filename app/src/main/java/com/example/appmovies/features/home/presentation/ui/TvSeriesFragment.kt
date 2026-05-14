package com.example.appmovies.features.home.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovies.R
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.appmovies.features.home.presentation.adapter.ListMovieAdapter
import com.example.moviesapp.Movie
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.fragment.app.viewModels
import com.example.appmovies.features.home.presentation.viewmodel.TvSeriesViewModel

class TvSeriesFragment : Fragment() {

    private val viewModel: TvSeriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv_series, container, false)
        
        setupRecyclerView(view)
        
        return view
    }
    
    private fun setupRecyclerView(view: View) {
        val recyclerViewTrending = view.findViewById<RecyclerView>(R.id.recyclerViewTrendingTv)
        recyclerViewTrending.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        
        val recyclerViewTopRated = view.findViewById<RecyclerView>(R.id.recyclerViewTopRatedTv)
        recyclerViewTopRated.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        
        lifecycleScope.launch {
            viewModel.trendingTv.collect { trending ->
                recyclerViewTrending.adapter = ListMovieAdapter(trending) { movie ->
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra("movie", movie)
                    startActivity(intent)
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.topRatedTv.collect { topRated ->
                recyclerViewTopRated.adapter = ListMovieAdapter(topRated) { movie ->
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra("movie", movie)
                    startActivity(intent)
                }
            }
        }
    }
}
