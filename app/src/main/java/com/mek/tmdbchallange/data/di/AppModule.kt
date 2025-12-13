package com.mek.tmdbchallange.data.di

import com.mek.tmdbchallange.data.remote.MovieApi
import com.mek.tmdbchallange.data.repository.MovieRepositoryImpl
import com.mek.tmdbchallange.domain.repository.MovieRepository
import com.mek.tmdbchallange.util.Constants.BASE_URL
import com.mek.tmdbchallange.util.Constants.BEARER_TOKEN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi() : MovieApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        // client servisden gelen mesajları okumak için yazıldı
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", BEARER_TOKEN)
                    .addHeader("accept", "application/json")
                    .build()
                chain.proceed(newRequest)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api : MovieApi) : MovieRepository {
        return MovieRepositoryImpl(api = api)
    }


}