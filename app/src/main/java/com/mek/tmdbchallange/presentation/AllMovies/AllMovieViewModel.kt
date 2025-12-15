package com.mek.tmdbchallange.presentation.AllMovies

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mek.tmdbchallange.domain.usecase.NowPlayingUseCase
import com.mek.tmdbchallange.domain.usecase.PopularUseCase
import com.mek.tmdbchallange.domain.usecase.TopRatedUseCase
import com.mek.tmdbchallange.domain.usecase.UpcomingUseCase
import com.mek.tmdbchallange.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AllMovieViewModel @Inject constructor(
    private val nowPlayingUseCase: NowPlayingUseCase,
    private val popularUseCase: PopularUseCase,
    private val topRatedUseCase: TopRatedUseCase,
    private val upcomingUseCase: UpcomingUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AllMovieState())
    val state = _state.asStateFlow()

    private var isRequestInProgress = false

    private val categoryKey: String = savedStateHandle["category"] ?: ""

    init {
        val category = MovieCategory.values().find { it.key == categoryKey }
        if (category != null) {
            _state.update { it.copy(category = category) }
            loadMovies()
        } else {
            _state.update { it.copy(error = "Kategori bulunamadı") }
        }
    }

    fun onEvent(event: AllMovieEvent) {
        when (event) {
            AllMovieEvent.LoadMovies -> loadMovies()
            AllMovieEvent.LoadNextPage -> {
                if (!_state.value.isLoading && !_state.value.isEndReached) {
                    loadMovies()
                }
            }
        }
    }

    private fun loadMovies() {
        if (isRequestInProgress)  return
        isRequestInProgress = true
        val stateValue = _state.value
        val page = stateValue.currentPage
        val category = stateValue.category ?: return

        // gelen kategoriye göre filmleri getirmek için kullanılacak usecase'i seç

        val flow = when (category) {
            MovieCategory.NOW_PLAYING -> nowPlayingUseCase(page, null)
            MovieCategory.POPULAR -> popularUseCase(page, null)
            MovieCategory.TOP_RATED -> topRatedUseCase(page, null)
            MovieCategory.UPCOMING -> upcomingUseCase(page, null)
        }

        flow.onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    val newMovies = result.data ?: emptyList()
                    _state.update {
                        it.copy(
                            movies = it.movies + newMovies,
                            currentPage = it.currentPage + 1,
                            isLoading = false,
                            isEndReached = newMovies.isEmpty()
                        )
                    }
                    isRequestInProgress = false
                    Log.d("AllMovieVM", "Loaded page ${stateValue.currentPage}")
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                    isRequestInProgress = false
                }
            }
        }.launchIn(viewModelScope)
    }
}