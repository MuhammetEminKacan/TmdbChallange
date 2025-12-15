package com.mek.tmdbchallange.data.remote.interceptor

import com.mek.tmdbchallange.data.local.LanguageDataSource
import com.mek.tmdbchallange.util.Constants.BEARER_TOKEN
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response

// api ye istek atarken dil parametresini kullancının profil alanından seçtiği türe göre yollamak için yazıldı

class LanguageInterceptor @Inject constructor(
    private val languageDataSource: LanguageDataSource
) : Interceptor {

    @Volatile
    private var currentLanguage: String = "en-US"

    init {
        CoroutineScope(Dispatchers.IO).launch {
            languageDataSource.languageFlow.collect {
                currentLanguage = it
            }
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("language", currentLanguage)
            .build()

        val newRequest = original.newBuilder()
            .url(newUrl)
            .addHeader("Authorization", BEARER_TOKEN)
            .addHeader("accept", "application/json")
            .build()

        return chain.proceed(newRequest)
    }
}