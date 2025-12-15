package com.mek.tmdbchallange.domain.model

data class MovieDetail(
    val id : Int,
    val title : String,
    val posterPath : String,
    val overview : String,
    val releaseDate : String,
    val voteAverage : Double,
    val originalLanguage : String,
    val genres : List<Genre>
)
