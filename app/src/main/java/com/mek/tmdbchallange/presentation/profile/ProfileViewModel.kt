package com.mek.tmdbchallange.presentation.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.NameChanged -> {
                _state.update { it.copy(name = event.name) }
            }
            is ProfileEvent.SurnameChanged -> {
                _state.update { it.copy(surname = event.surname) }
            }
            is ProfileEvent.LanguageChanged -> {
                _state.update { it.copy(selectedLanguage = event.language) }
            }
            ProfileEvent.SaveChanges -> {
                // TODO
            }
        }
    }
}

