package com.mek.tmdbchallange.data.repository

import com.mek.tmdbchallange.data.remote.MovieApi
import com.mek.tmdbchallange.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api : MovieApi
) : MovieRepository {
}