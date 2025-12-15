package com.mek.tmdbchallange.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mek.tmdbchallange.domain.usecase.MovieDetailUseCase
import com.mek.tmdbchallange.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: MovieDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    private val movieId: Int = savedStateHandle["id"] ?: -1

    init {
        if (movieId != -1) {
            getMovieDetail(movieId)
        } else {
            _state.update {
                it.copy(error = "Film bilgisi alınamadı")
            }
        }
    }

    private fun getMovieDetail(movieId: Int) {
        getMovieDetailUseCase(movieId = movieId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.message) }
                }
                is Resource.Success -> {
                    _state.update { it.copy(isLoading = false, movieDetail = result.data) }
                }
            }
        }.launchIn(viewModelScope)
    }


}