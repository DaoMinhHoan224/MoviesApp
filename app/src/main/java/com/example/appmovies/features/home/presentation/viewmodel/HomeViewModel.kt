package com.example.appmovies.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovies.core.repository.MovieRepository
import com.example.moviesapp.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = MovieRepository()

    private val _nowPlayingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val nowPlayingMovies: StateFlow<List<Movie>> = _nowPlayingMovies.asStateFlow()

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> = _popularMovies.asStateFlow()
    
    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                // Soon Movies -> Upcoming
                val upcoming = repository.getUpcomingMovies().map { it.toMovie() }
                _nowPlayingMovies.value = upcoming
                
                val popular = repository.getPopularMovies().map { it.toMovie() }
                _popularMovies.value = popular
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
