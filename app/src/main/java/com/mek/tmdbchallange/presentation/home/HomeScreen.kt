package com.mek.tmdbchallange.presentation.home

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mek.tmdbchallange.R
import com.mek.tmdbchallange.domain.model.Movie
import com.mek.tmdbchallange.presentation.home.components.MovieListItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeEvent.LoadHomeData)
    }

    HomeScreenContent(state = state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(state: HomeState) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CineHub") },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(painter = painterResource(id = R.drawable.ic_moon), contentDescription = "Theme")
                    }
                    IconButton(onClick = { /* TODO */ }) {
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
            Text(text = "Hoş Geldiniz! 👋", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "En iyi filmleri keşfedin", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))

            Spacer(modifier = Modifier.height(24.dp))

            if (state.isNowPlayingLoading || state.isPopularLoading || state.isTopRatedLoading ||state.isUpcomingLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else if (state.error != null) {
                Text(text = state.error, color = MaterialTheme.colorScheme.error, modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                MovieCategorySection(title = "now playing", movies = state.nowPlayingMovies)
                Spacer(modifier = Modifier.height(16.dp))
                MovieCategorySection(title = "popular" , movies = state.popularMovies)
                Spacer(modifier = Modifier.height(16.dp))
                MovieCategorySection(title = "top rated" , movies = state.topRatedMovies)
                Spacer(modifier = Modifier.height(16.dp))
                MovieCategorySection(title = "Upcoming" , movies = state.upcomingMovies)
            }
        }
    }
}

@Composable
fun MovieCategorySection(
    title: String,
    movies: List<Movie>
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "Tümünü Gör >",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(movies) {
                MovieListItem(movie = it)
            }
        }
    }
}
