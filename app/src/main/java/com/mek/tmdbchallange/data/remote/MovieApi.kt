package com.mek.tmdbchallange.data.remote

import com.mek.tmdbchallange.data.remote.dtos.MovieDetailResponseDto
import com.mek.tmdbchallange.data.remote.dtos.MovieListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int,
        @Query("region") region: String?
    ) : MovieListResponseDto

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int,
        @Query("region") region: String?
    ) : MovieListResponseDto

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int,
        @Query("region") region: String?
    ) : MovieListResponseDto

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int,
        @Query("region") region: String?
    ) : MovieListResponseDto

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false
    ): MovieListResponseDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
    ): MovieDetailResponseDto
}