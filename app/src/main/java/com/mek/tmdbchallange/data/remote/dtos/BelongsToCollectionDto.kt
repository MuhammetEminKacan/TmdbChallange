package com.mek.tmdbchallange.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class BelongsToCollectionDto(
    val id: Int,
    val name: String,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?
)
