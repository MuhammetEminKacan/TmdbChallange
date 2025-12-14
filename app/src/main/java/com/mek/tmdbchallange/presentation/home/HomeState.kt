package com.mek.tmdbchallange.presentation.home


import com.mek.tmdbchallange.domain.model.Movie

data class HomeState(
    val isNowPlayingLoading: Boolean = false,
    val isPopularLoading: Boolean = false,
    val isTopRatedLoading: Boolean = false,
    val isUpcomingLoading : Boolean = false,
    val nowPlayingMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val topRatedMovies: List<Movie> = emptyList(),
    val upcomingMovies: List<Movie> = emptyList(),
    val error: String? = null
)
