package com.mek.tmdbchallange.presentation.search

sealed class SearchEvent {
    data class OnQueryChanged(val query: String) : SearchEvent()
    object SearchMovies : SearchEvent()
}
