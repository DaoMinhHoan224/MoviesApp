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
import com.example.appmovies.features.home.presentation.adapter.SoonMovieAdapter
import com.example.moviesapp.Actor
import com.example.moviesapp.Movie

class FilmsFragment : Fragment() {

    private var _binding: FragmentFilmsBinding? = null
    private val binding get() = _binding!!

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

        val movies = listOf(
            Movie(1, "Thor: Love & Thunder", "Action • Adventure", "https://image.tmdb.org/t/p/w500/pIkRyD18kl4FhoCNQuWxWu5c1El.jpg"),
            Movie(2, "Into the Spider-Verse", "Animation • Action", "https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg"),
            Movie(3, "John Wick: Chapter 4", "Action • Thriller", "https://image.tmdb.org/t/p/w500/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg")
        )

        binding.recyclerViewPopularCaMo.adapter = SoonMovieAdapter(movies) { movie ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("movie", movie)
            startActivity(intent)
        }
    }

    private fun setupPopularActors() {
        binding.recyclerViewActors.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val actors = listOf(
            Actor(1, "Samuel L. Jackson", 5, "https://image.tmdb.org/t/p/w500/86YpS69Yw9Zps1MvL59pUfRkSOn.jpg"),
            Actor(2, "Dwayne Johnson", 3, "https://image.tmdb.org/t/p/w500/cgYQXpY21vX6Mws95Y99t9M9U8I.jpg"),
            Actor(3, "Margot Robbie", 4, "https://image.tmdb.org/t/p/w500/3o9p9S9Yw9Zps1MvL59pUfRkSOn.jpg"), // Note: using actual actor images from TMDB would be better
            Actor(4, "Will Smith", 3, "https://image.tmdb.org/t/p/w500/76YpS69Yw9Zps1MvL59pUfRkSOn.jpg")
        )

        // Using placeholder images for demo if TMDB links are broken
        val actorsWithPlaceholders = listOf(
            Actor(1, "Samuel L. Jackson", 5, "https://i.pravatar.cc/150?u=samuel"),
            Actor(2, "Dwayne Johnson", 3, "https://i.pravatar.cc/150?u=dwayne"),
            Actor(3, "Margot Robbie", 4, "https://i.pravatar.cc/150?u=margot"),
            Actor(4, "Will Smith", 3, "https://i.pravatar.cc/150?u=will")
        )

        binding.recyclerViewActors.adapter = ActorAdapter(actorsWithPlaceholders) { actor ->
            // Handle actor click if needed
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
