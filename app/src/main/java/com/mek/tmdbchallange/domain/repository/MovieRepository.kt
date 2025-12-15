package com.mek.tmdbchallange.domain.repository

import com.mek.tmdbchallange.domain.model.Movie
import com.mek.tmdbchallange.domain.model.MovieDetail
import com.mek.tmdbchallange.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getNowPlaying(
        page: Int,
        region: String?
    ) : Flow<Resource<List<Movie>>>

    fun getPopular(
        page: Int,
        region: String?
    ) : Flow<Resource<List<Movie>>>

    fun getTopRated(
        page: Int,
        region: String?
    ) : Flow<Resource<List<Movie>>>

    fun getUpcoming(
        page: Int,
        region: String?
    ) : Flow<Resource<List<Movie>>>

    fun searchMovies(
        query: String,
        page: Int,
        includeAdult: Boolean
    ) : Flow<Resource<List<Movie>>>

    fun getMovieDetail(
        movieId: Int
    ) : Flow<Resource<MovieDetail>>
}