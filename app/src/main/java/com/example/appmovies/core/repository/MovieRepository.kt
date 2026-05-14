package com.example.appmovies.core.repository

import com.example.appmovies.core.network.RetrofitClient
import com.example.appmovies.core.network.model.TmdbCreditsResponse
import com.example.appmovies.core.network.model.TmdbMovie
import com.example.appmovies.core.network.model.TmdbPerson
import com.example.appmovies.core.network.model.TmdbReviewResponse

class MovieRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getPopularMovies(): List<TmdbMovie> {
        return apiService.getPopularMovies().results
    }

    suspend fun getNowPlayingMovies(): List<TmdbMovie> {
        return apiService.getNowPlayingMovies().results
    }

    suspend fun getTopRatedMovies(): List<TmdbMovie> {
        return apiService.getTopRatedMovies().results
    }

    suspend fun getUpcomingMovies(): List<TmdbMovie> {
        return apiService.getUpcomingMovies().results
    }

    suspend fun getPopularActors(): List<TmdbPerson> {
        return apiService.getPopularActors().results
    }

    suspend fun getPopularTv(): List<TmdbMovie> {
        return apiService.getPopularTv().results
    }

    suspend fun getTopRatedTv(): List<TmdbMovie> {
        return apiService.getTopRatedTv().results
    }

    suspend fun getTrendingMovies(): List<TmdbMovie> {
        return apiService.getTrendingMovies().results
    }

    suspend fun getMovieDetails(movieId: Int): TmdbMovie {
        return apiService.getMovieDetails(movieId)
    }

    suspend fun searchMovies(query: String): List<TmdbMovie> {
        return apiService.searchMovies(query).results
    }

    suspend fun getMovieCredits(movieId: Int): TmdbCreditsResponse {
        return apiService.getMovieCredits(movieId)
    }

    suspend fun getMovieReviews(movieId: Int): TmdbReviewResponse {
        return apiService.getMovieReviews(movieId)
    }
}
