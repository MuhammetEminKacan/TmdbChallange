package com.mek.tmdbchallange.util

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

// uygulama içindeki api den gelmeyen yazıların kullanıcnın profilde belirttiği dil seçimine göre gelmesi için yazıldı

object LocaleHelper {
    fun setLocale(context: Context, languageCode: String) {
        val locale = Locale.forLanguageTag(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )
    }
}