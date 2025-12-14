package com.mek.tmdbchallange.data.mapper

import com.mek.tmdbchallange.data.remote.dtos.MovieDto
import com.mek.tmdbchallange.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath ?: ""
    )
}