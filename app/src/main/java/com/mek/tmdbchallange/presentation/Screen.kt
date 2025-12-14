package com.mek.tmdbchallange.presentation

sealed class Screen(val route : String) {
    object HomeScreen : Screen(route = "home_screen")

    object ProfileScreen : Screen(route = "profile_screen")
}