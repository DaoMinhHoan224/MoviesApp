package com.example.appmovies.core.network.model

import com.google.gson.annotations.SerializedName

data class TmdbReviewResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val results: List<TmdbReview>
)

data class TmdbReview(
    @SerializedName("id") val id: String,
    @SerializedName("author") val author: String,
    @SerializedName("content") val content: String,
    @SerializedName("created_at") val createdAt: String
)
