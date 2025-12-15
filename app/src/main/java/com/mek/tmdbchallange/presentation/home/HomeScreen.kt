package com.mek.tmdbchallange.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.mek.tmdbchallange.R
import com.mek.tmdbchallange.domain.model.Movie
import com.mek.tmdbchallange.presentation.AllMovies.MovieCategory
import com.mek.tmdbchallange.presentation.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeEvent.LoadHomeData)
    }

    HomeScreenContent(
        state = state,
        onMovieClick = { movie ->
            navController.navigate(Screen.MovieDetailScreen.createRoute(movie.id))
        },
        onAllMovieClick = { category ->
            navController.navigate(Screen.AllMovieScreen.createRoute(category = category))
        },
        onProfileClick = {
            navController.navigate(Screen.ProfileScreen.route)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(state: HomeState,
                      onProfileClick: () -> Unit,
                      onMovieClick: (Movie) -> Unit,
                      onAllMovieClick: (MovieCategory) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = onProfileClick) {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = stringResource(R.string.welcome), fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = stringResource(R.string.discover_movies), fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))

            Spacer(modifier = Modifier.height(24.dp))

            if (state.isNowPlayingLoading || state.isPopularLoading || state.isTopRatedLoading ||state.isUpcomingLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else if (state.error != null) {
                Text(text = state.error, color = MaterialTheme.colorScheme.error, modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                MovieCategorySection(title = stringResource(R.string.now_playing), movies = state.nowPlayingMovies,
                    category = MovieCategory.NOW_PLAYING,onMovieClick = onMovieClick, onAllMovieClick = onAllMovieClick)
                Spacer(modifier = Modifier.height(16.dp))
                MovieCategorySection(title = stringResource(R.string.popular), movies = state.popularMovies,
                    category = MovieCategory.POPULAR,onMovieClick = onMovieClick, onAllMovieClick = onAllMovieClick)
                Spacer(modifier = Modifier.height(16.dp))
                MovieCategorySection(title = stringResource(R.string.top_rated) , movies = state.topRatedMovies,
                    category = MovieCategory.TOP_RATED,onMovieClick = onMovieClick, onAllMovieClick = onAllMovieClick)
                Spacer(modifier = Modifier.height(16.dp))
                MovieCategorySection(title = stringResource(R.string.upcoming) , movies = state.upcomingMovies,
                    category = MovieCategory.UPCOMING,onMovieClick = onMovieClick, onAllMovieClick = onAllMovieClick)
            }
        }
    }
}

@Composable
fun MovieCategorySection(
    title: String,
    movies: List<Movie>,
    category : MovieCategory,
    onMovieClick: (Movie) -> Unit,
    onAllMovieClick: (MovieCategory) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(
                text = stringResource(R.string.see_all),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable{
                    onAllMovieClick(category)
                }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(movies) {
                MovieListItem(movie = it, onClick = onMovieClick)
            }
        }
    }
}
