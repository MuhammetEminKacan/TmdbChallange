package com.mek.tmdbchallange.presentation.home

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
class HomeViewModel @Inject constructor(
    private val nowPlayingUseCase: NowPlayingUseCase,
    private val popularUseCase: PopularUseCase,
    private val topRatedUseCase: TopRatedUseCase,
    private val upcomingUseCase: UpcomingUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.LoadHomeData -> {
                getNowPlayingMovies()
                getPopularMovies()
                getTopRatedMovies()
                getUpcomingMovies()
            }
        }
    }

    private fun getNowPlayingMovies() {
        nowPlayingUseCase("en-US", 1, null).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            nowPlayingMovies = result.data ?: emptyList(),
                            isNowPlayingLoading = false
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            error = result.message,
                            isNowPlayingLoading = false
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(isNowPlayingLoading = true)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getPopularMovies() {
        popularUseCase("en-US", 1, null).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            popularMovies = result.data ?: emptyList(),
                            isPopularLoading = false
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            error = result.message,
                            isPopularLoading = false
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(isPopularLoading = true)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTopRatedMovies() {
        topRatedUseCase.invoke("en-US", 1, null).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            topRatedMovies = result.data ?: emptyList(),
                            isTopRatedLoading = false
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            error = result.message,
                            isTopRatedLoading = false
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(isTopRatedLoading = true)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUpcomingMovies() {
        upcomingUseCase.invoke("en-US", 1, null).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            upcomingMovies = result.data ?: emptyList(),
                            isUpcomingLoading = false
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            error = result.message,
                            isUpcomingLoading = false
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(isUpcomingLoading = true)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}
