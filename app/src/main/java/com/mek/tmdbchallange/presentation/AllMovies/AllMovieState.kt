package com.mek.tmdbchallange.presentation.AllMovies

import com.mek.tmdbchallange.domain.model.Movie

data class AllMovieState(
    val category: MovieCategory? = null,
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 1,
    val isEndReached: Boolean = false
)