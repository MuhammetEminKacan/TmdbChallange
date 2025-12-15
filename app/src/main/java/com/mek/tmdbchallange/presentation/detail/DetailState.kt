package com.mek.tmdbchallange.presentation.detail

import com.mek.tmdbchallange.domain.model.MovieDetail

data class DetailState(
    val movieDetail: MovieDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)