package com.mek.tmdbchallange.presentation.profile

import com.mek.tmdbchallange.presentation.profile.components.Language

sealed class ProfileEvent {
    data class NameChanged(val name: String) : ProfileEvent()
    data class SurnameChanged(val surname: String) : ProfileEvent()
    data class LanguageChanged(val language: Language) : ProfileEvent()
    data class PhotoSelected(val uri: String) : ProfileEvent()
    object SaveChanges : ProfileEvent()
}
