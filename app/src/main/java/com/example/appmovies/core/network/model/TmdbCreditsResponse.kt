package com.example.appmovies.core.network.model

import com.google.gson.annotations.SerializedName

data class TmdbCreditsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val cast: List<TmdbCast>
)

data class TmdbCast(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("character") val character: String?,
    @SerializedName("profile_path") val profilePath: String?
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
            movieCount = 0, // Using 0 as default since character is not an int, maybe could use character name elsewhere
            imageUrl = getFullProfileUrl()
        )
    }
}
