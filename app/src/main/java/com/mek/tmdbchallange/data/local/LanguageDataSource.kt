package com.mek.tmdbchallange.data.local

import com.mek.tmdbchallange.data.local.dao.ProfileDao
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LanguageDataSource @Inject constructor(
    private val profileDao: ProfileDao
) {
    val languageFlow: Flow<String> =
        profileDao.observeLanguage()
            .map { it ?: "en-US" }
}