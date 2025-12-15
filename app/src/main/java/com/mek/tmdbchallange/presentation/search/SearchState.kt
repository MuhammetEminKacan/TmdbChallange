package com.mek.tmdbchallange.presentation.search

import com.mek.tmdbchallange.domain.model.Movie

data class SearchState(
    val query: String = "",
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
