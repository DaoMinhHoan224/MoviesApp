package com.example.appmovies.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovies.core.repository.MovieRepository
import com.example.moviesapp.Actor
import com.example.moviesapp.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmsViewModel : ViewModel() {
    private val repository = MovieRepository()

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> = _popularMovies.asStateFlow()

    private val _popularActors = MutableStateFlow<List<Actor>>(emptyList())
    val popularActors: StateFlow<List<Actor>> = _popularActors.asStateFlow()

    init {
        fetchFilmsData()
    }

    private fun fetchFilmsData() {
        viewModelScope.launch {
            try {
                val movies = repository.getPopularMovies().map { it.toMovie() }
                _popularMovies.value = movies
                
                val actors = repository.getPopularActors().map { it.toActor() }
                _popularActors.value = actors
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
