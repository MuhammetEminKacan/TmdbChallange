package com.mek.tmdbchallange.presentation.AllMovies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.mek.tmdbchallange.domain.model.Movie
import com.mek.tmdbchallange.presentation.Screen
import com.mek.tmdbchallange.presentation.search.MovieSearchItem

@Composable
fun AllMovieScreen(
    navController: NavController,
    viewModel: AllMovieViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    AllMovieContent(
        state = state,
        onLoadNextPage = {
            viewModel.onEvent(AllMovieEvent.LoadNextPage)
        },
        onMovieClick = {
            navController.navigate(Screen.MovieDetailScreen.createRoute(it.id))
        }
    )
}
@Composable
fun AllMovieContent(
    state: AllMovieState,
    onLoadNextPage: () -> Unit,
    onMovieClick: (Movie) -> Unit
) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        // infinite scrolling yapısı için
        snapshotFlow {
            val layoutInfo = listState.layoutInfo
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()

            lastVisibleItem != null &&
                    lastVisibleItem.index == layoutInfo.totalItemsCount - 1 &&
                    !state.isLoading &&
                    !state.isEndReached
        }.collect { shouldLoad ->
            if (shouldLoad) {
                onLoadNextPage()
            }
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = state.movies,
            key = { it.id }
        ) { movie ->
            MovieSearchItem(
                movie = movie,
                onMovieClick = onMovieClick
            )
        }

        if (state.isLoading) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}