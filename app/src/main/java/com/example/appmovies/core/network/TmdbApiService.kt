package com.example.appmovies.core.network

import com.example.appmovies.core.network.model.TmdbCreditsResponse
import com.example.appmovies.core.network.model.TmdbMovie
import com.example.appmovies.core.network.model.TmdbPerson
import com.example.appmovies.core.network.model.TmdbResponse
import com.example.appmovies.core.network.model.TmdbReviewResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Body
import com.google.gson.JsonObject

interface TmdbApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): TmdbResponse<TmdbMovie>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): TmdbResponse<TmdbMovie>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): TmdbResponse<TmdbMovie>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): TmdbResponse<TmdbMovie>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): TmdbMovie

    @GET("person/popular")
    suspend fun getPopularActors(): TmdbResponse<TmdbPerson>

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String): TmdbResponse<TmdbMovie>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(@Path("movie_id") movieId: Int): TmdbReviewResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Int): TmdbCreditsResponse
    
    // Using JsonObject for rate body: {"value": 8.5}
    @POST("movie/{movie_id}/rating")
    suspend fun rateMovie(
        @Path("movie_id") movieId: Int, 
        @Body rating: JsonObject,
        @Query("guest_session_id") guestSessionId: String? = null
    ): JsonObject
    
    // TV endpoints for TV Series Tab
    @GET("tv/popular")
    suspend fun getPopularTv(): TmdbResponse<TmdbMovie>
    
    @GET("tv/top_rated")
    suspend fun getTopRatedTv(): TmdbResponse<TmdbMovie>
    
    // Discover endpoints for Look Tab (just getting some random/trending data)
    @GET("trending/movie/day")
    suspend fun getTrendingMovies(): TmdbResponse<TmdbMovie>
}
