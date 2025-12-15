package com.mek.tmdbchallange.data.mapper

import com.mek.tmdbchallange.data.remote.dtos.GenreDto
import com.mek.tmdbchallange.data.remote.dtos.MovieDetailResponseDto
import com.mek.tmdbchallange.data.remote.dtos.MovieDto
import com.mek.tmdbchallange.domain.model.Genre
import com.mek.tmdbchallange.domain.model.Movie
import com.mek.tmdbchallange.domain.model.MovieDetail

// dto sınıflarını model  sınıfına dönüştüren fonksiyonlar
fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath ?: ""
    )
}

fun MovieDetailResponseDto.toDomain() : MovieDetail {
    return MovieDetail(
        id = id,
        title = title,
        posterPath = posterPath ?: "",
        overview = overview ?: "",
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        originalLanguage = originalLanguage,
        genres = genres.map { it.toDomain() }
    )
}

fun GenreDto.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}