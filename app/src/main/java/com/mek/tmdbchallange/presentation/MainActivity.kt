package com.mek.tmdbchallange.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mek.tmdbchallange.presentation.home.HomeScreen
import com.mek.tmdbchallange.presentation.navigation.BottomNavItem
import com.mek.tmdbchallange.presentation.navigation.components.BottomNavigationBar
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
                        composable(BottomNavItem.Home.route) {
                            HomeScreen()
                        }
                        composable(BottomNavItem.Search.route) {
                            SearchScreen()
                        }
                        composable("profile_screen") { // Keep profile screen accessible
                            ProfileScreen()
                        }
                    }
                }
            }
        }
    }
}
