package com.mek.tmdbchallange.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mek.tmdbchallange.presentation.AllMovies.AllMovieScreen
import com.mek.tmdbchallange.presentation.home.HomeScreen
import com.mek.tmdbchallange.presentation.bottom_navigation.BottomNavItem
import com.mek.tmdbchallange.presentation.bottom_navigation.BottomNavigationBar
import com.mek.tmdbchallange.presentation.detail.MovieDetailScreen
import com.mek.tmdbchallange.presentation.profile.ProfileScreen
import com.mek.tmdbchallange.presentation.search.SearchScreen
import com.mek.tmdbchallange.presentation.ui.theme.TmdbChallangeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TmdbChallangeTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = BottomNavItem.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = BottomNavItem.Home.route) {
                            HomeScreen(navController = navController)
                        }
                        composable(route = BottomNavItem.Search.route) {
                            SearchScreen(navController = navController)
                        }
                        composable(route = Screen.ProfileScreen.route) {
                            ProfileScreen()
                        }
                        composable(
                           route = Screen.MovieDetailScreen.route,
                            arguments =listOf(navArgument("id"){ type = NavType.IntType })
                        ) {
                            MovieDetailScreen()
                        }
                        composable(
                            route = Screen.AllMovieScreen.route,
                            arguments = listOf(
                                navArgument("category") { type = NavType.StringType }
                            )
                        ) {
                            AllMovieScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
