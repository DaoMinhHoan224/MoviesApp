package com.example.appmovies.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovies.core.repository.MovieRepository
import com.example.moviesapp.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LookViewModel : ViewModel() {
    private val repository = MovieRepository()

    private val _trendingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val trendingMovies: StateFlow<List<Movie>> = _trendingMovies.asStateFlow()

    init {
        fetchLookData()
    }

    private fun fetchLookData() {
        viewModelScope.launch {
            try {
                val trending = repository.getTrendingMovies().map { it.toMovie() }
                _trendingMovies.value = trending
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
