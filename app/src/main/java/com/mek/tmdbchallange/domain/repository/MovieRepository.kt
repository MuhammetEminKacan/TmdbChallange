package com.mek.tmdbchallange.domain.repository

import com.mek.tmdbchallange.domain.model.Movie
import com.mek.tmdbchallange.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getNowPlaying(
        language: String,
        page: Int,
        region: String?
    ) : Flow<Resource<List<Movie>>>

    fun getPopular(
        language: String,
        page: Int,
        region: String?
    ) : Flow<Resource<List<Movie>>>

    fun getTopRated(
        language: String,
        page: Int,
        region: String?
    ) : Flow<Resource<List<Movie>>>

    fun getUpcoming(
        language: String,
        page: Int,
        region: String?
    ) : Flow<Resource<List<Movie>>>
}