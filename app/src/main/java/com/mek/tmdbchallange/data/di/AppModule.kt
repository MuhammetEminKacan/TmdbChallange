package com.mek.tmdbchallange.data.di

import android.content.Context
import androidx.room.Room
import com.mek.tmdbchallange.data.local.AppDatabase
import com.mek.tmdbchallange.data.local.LanguageDataSource
import com.mek.tmdbchallange.data.local.dao.ProfileDao
import com.mek.tmdbchallange.data.remote.MovieApi
import com.mek.tmdbchallange.data.remote.interceptor.LanguageInterceptor
import com.mek.tmdbchallange.data.repository.MovieRepositoryImpl
import com.mek.tmdbchallange.data.repository.ProfileRepositoryImpl
import com.mek.tmdbchallange.domain.repository.MovieRepository
import com.mek.tmdbchallange.domain.repository.ProfileRepository
import com.mek.tmdbchallange.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideMovieApi(
        languageInterceptor: LanguageInterceptor  // apiye istek atarken dil parametresini ayarlamak için
    ) : MovieApi {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(languageInterceptor)
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

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "tmdb_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProfileDao(db: AppDatabase): ProfileDao {
        return db.profileDao()
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        dao: ProfileDao
    ): ProfileRepository {
        return ProfileRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideLanguageDataSource(
        profileDao: ProfileDao
    ): LanguageDataSource {
        return LanguageDataSource(profileDao)
    }

    @Provides
    @Singleton
    fun provideLanguageInterceptor(
        languageDataSource: LanguageDataSource
    ): LanguageInterceptor {
        return LanguageInterceptor(languageDataSource)
    }


}