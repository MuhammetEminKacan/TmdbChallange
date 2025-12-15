package com.mek.tmdbchallange.presentation

import com.mek.tmdbchallange.presentation.AllMovies.MovieCategory

sealed class Screen(val route : String) {
    object HomeScreen : Screen(route = "home_screen")
    object SearchScreen : Screen(route = "search_screen")
    object ProfileScreen : Screen(route = "profile_screen")
    object MovieDetailScreen : Screen(route = "movie_detail_screen/{id}") {
        fun createRoute(id : Int) = "movie_detail_screen/$id"
    }
    object AllMovieScreen : Screen(route = "all_movie_screen/{category}") {
        fun createRoute(category : MovieCategory) = "all_movie_screen/${category.key}"
    }


}