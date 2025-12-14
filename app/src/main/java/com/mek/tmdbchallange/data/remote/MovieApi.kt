package com.mek.tmdbchallange.data.remote

import com.mek.tmdbchallange.data.remote.dtos.MovieListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String?
    ) : MovieListResponseDto

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String?
    ) : MovieListResponseDto

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String?
    ) : MovieListResponseDto

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String?
    ) : MovieListResponseDto
}