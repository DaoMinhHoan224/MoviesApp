package com.example.appmovies.features.home.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovies.databinding.FragmentFilmsBinding
import com.example.appmovies.features.home.presentation.adapter.ActorAdapter
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.moviesapp.Actor
import com.example.moviesapp.Movie
import androidx.fragment.app.viewModels
import com.example.appmovies.features.home.presentation.viewmodel.FilmsViewModel
import com.example.appmovies.features.home.presentation.adapter.SoonMovieAdapter

class FilmsFragment : Fragment() {

    private var _binding: FragmentFilmsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FilmsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPopularCaMo()
        setupPopularActors()
    }

    private fun setupPopularCaMo() {
        binding.recyclerViewPopularCaMo.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        lifecycleScope.launch {
            viewModel.popularMovies.collect { movies ->
                binding.recyclerViewPopularCaMo.adapter = SoonMovieAdapter(movies) { movie ->
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra("movie", movie)
                    startActivity(intent)
                }
            }
        }
    }

    private fun setupPopularActors() {
        binding.recyclerViewActors.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        lifecycleScope.launch {
            viewModel.popularActors.collect { actors ->
                binding.recyclerViewActors.adapter = ActorAdapter(actors) { actor ->
                    // Handle actor click if needed
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
