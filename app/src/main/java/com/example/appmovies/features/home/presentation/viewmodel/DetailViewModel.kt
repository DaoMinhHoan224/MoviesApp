package com.example.appmovies.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovies.core.network.model.TmdbCreditsResponse
import com.example.appmovies.core.network.model.TmdbMovie
import com.example.appmovies.core.network.model.TmdbReviewResponse
import com.example.appmovies.core.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val repository = MovieRepository()

    private val _movieDetails = MutableStateFlow<TmdbMovie?>(null)
    val movieDetails: StateFlow<TmdbMovie?> = _movieDetails.asStateFlow()

    private val _credits = MutableStateFlow<TmdbCreditsResponse?>(null)
    val credits: StateFlow<TmdbCreditsResponse?> = _credits.asStateFlow()

    private val _reviews = MutableStateFlow<TmdbReviewResponse?>(null)
    val reviews: StateFlow<TmdbReviewResponse?> = _reviews.asStateFlow()

    fun fetchDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val details = repository.getMovieDetails(movieId)
                _movieDetails.value = details
                
                val credits = repository.getMovieCredits(movieId)
                _credits.value = credits
                
                val reviews = repository.getMovieReviews(movieId)
                _reviews.value = reviews
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
