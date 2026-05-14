package com.example.appmovies.core.network.model

import com.google.gson.annotations.SerializedName

data class TmdbMovie(
    @SerializedName("id") val id: Int,
    @SerializedName("title", alternate = ["name"]) val title: String?, // name is for TV series
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date", alternate = ["first_air_date"]) val releaseDate: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("genres") val genres: List<TmdbGenre>? = null
) {
    fun getFullPosterUrl(): String {
        return if (posterPath != null) {
            "https://image.tmdb.org/t/p/w500$posterPath"
        } else {
            ""
        }
    }
    
    fun toMovie(): com.example.moviesapp.Movie {
        return com.example.moviesapp.Movie(
            id = id,
            title = title ?: "Unknown",
            subtitle = releaseDate ?: "TBA",
            imageUrl = getFullPosterUrl()
        )
    }
}
