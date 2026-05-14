package com.example.appmovies.features.home.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.appmovies.R
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.appmovies.features.home.presentation.adapter.LookAdapter
import com.example.moviesapp.Movie
import androidx.fragment.app.viewModels
import com.example.appmovies.features.home.presentation.viewmodel.LookViewModel

class LookFragment : Fragment() {

    private val viewModel: LookViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_look, container, false)
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewLook)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        
        // Snap to center like shorts
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        
        lifecycleScope.launch {
            viewModel.trendingMovies.collect { lookItems ->
                val adapter = LookAdapter(lookItems) { movie ->
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra("movie", movie)
                    startActivity(intent)
                }
                recyclerView.adapter = adapter
            }
        }
        
        return view
    }
}
