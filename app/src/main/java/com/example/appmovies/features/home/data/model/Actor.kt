package com.example.moviesapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Actor(
    val id: Int,
    val name: String,
    val movieCount: Int,
    val imageUrl: String
) : Parcelable
