package com.mek.tmdbchallange.presentation.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.mek.tmdbchallange.presentation.Screen

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : BottomNavItem(Screen.HomeScreen.route, Icons.Default.Home, "Ana Sayfa")
    object Search : BottomNavItem(Screen.SearchScreen.route, Icons.Default.Search, "Arama")
}
