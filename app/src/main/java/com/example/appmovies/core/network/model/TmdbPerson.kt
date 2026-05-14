package com.example.appmovies.core.network.model

import com.google.gson.annotations.SerializedName

data class TmdbPerson(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("popularity") val popularity: Double?
) {
    fun getFullProfileUrl(): String {
        return if (profilePath != null) {
            "https://image.tmdb.org/t/p/w500$profilePath"
        } else {
            ""
        }
    }
    
    fun toActor(): com.example.moviesapp.Actor {
        return com.example.moviesapp.Actor(
            id = id,
            name = name,
            movieCount = popularity?.toInt() ?: 0, // Using popularity as movieCount for display purposes
            imageUrl = getFullProfileUrl()
        )
    }
}
