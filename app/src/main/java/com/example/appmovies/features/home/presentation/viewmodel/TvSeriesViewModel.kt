package com.example.appmovies.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovies.core.repository.MovieRepository
import com.example.moviesapp.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TvSeriesViewModel : ViewModel() {
    private val repository = MovieRepository()

    private val _trendingTv = MutableStateFlow<List<Movie>>(emptyList())
    val trendingTv: StateFlow<List<Movie>> = _trendingTv.asStateFlow()

    private val _topRatedTv = MutableStateFlow<List<Movie>>(emptyList())
    val topRatedTv: StateFlow<List<Movie>> = _topRatedTv.asStateFlow()

    init {
        fetchTvData()
    }

    private fun fetchTvData() {
        viewModelScope.launch {
            try {
                val trending = repository.getPopularTv().map { it.toMovie() }
                _trendingTv.value = trending
                
                val topRated = repository.getTopRatedTv().map { it.toMovie() }
                _topRatedTv.value = topRated
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
