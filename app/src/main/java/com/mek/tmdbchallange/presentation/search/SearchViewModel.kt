package com.mek.tmdbchallange.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mek.tmdbchallange.domain.usecase.SearchUseCase
import com.mek.tmdbchallange.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private var searchJob: Job? = null

    private companion object {
        const val DEBOUNCE_MS = 400L
        const val MIN_QUERY_LENGTH = 2
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnQueryChanged -> {
                _state.update { it.copy(query = event.query) }
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(DEBOUNCE_MS)
                    performSearch(immediate = false)
                }
            }

            SearchEvent.SearchMovies -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    performSearch(immediate = true)
                }
            }
        }
    }

    private suspend fun performSearch(immediate: Boolean) {
        val query = _state.value.query.trim()
        if (query.isBlank()) {
            _state.update {
                it.copy(
                    movies = emptyList(),
                    isLoading = false,
                    error = null
                )
            }
            return
        }

        if (!immediate && query.length < MIN_QUERY_LENGTH) {
            _state.update {
                it.copy(
                    movies = emptyList(),
                    isLoading = false,
                    error = null
                )
            }
            return
        }
        _state.update { it.copy(isLoading = true, error = null) }

        searchUseCase(
            query = query,
            page = 1,
            includeAdult = false
        ).collectLatest { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true, error = null) }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            movies = result.data ?: emptyList(),
                            isLoading = false,
                            error = null
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message ?: "Beklenmeyen bir hata oluştu",
                            movies = emptyList()
                        )
                    }
                }
            }
        }
    }
}