package com.mek.tmdbchallange.presentation.profile

import com.mek.tmdbchallange.presentation.profile.components.Language

data class ProfileState(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val selectedLanguage: Language = Language.ENGLISH,
    val isLoading: Boolean = false,
    val photoUri: String? = null,
    val isSavedSuccessfully: Boolean = false
)
