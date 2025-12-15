package com.mek.tmdbchallange.presentation.detail

sealed class DetailEvent {
    object LoadMovieDetail : DetailEvent()
}