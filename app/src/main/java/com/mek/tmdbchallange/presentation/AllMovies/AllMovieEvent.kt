package com.mek.tmdbchallange.presentation.AllMovies

sealed class AllMovieEvent {
    object LoadMovies : AllMovieEvent()
    object LoadNextPage : AllMovieEvent()
}