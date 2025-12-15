package com.mek.tmdbchallange.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mek.tmdbchallange.data.local.entity.ProfileEntity
import com.mek.tmdbchallange.domain.repository.ProfileRepository
import com.mek.tmdbchallange.presentation.profile.components.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            repository.getProfile()?.let { profile ->
                _state.update {
                    it.copy(
                        name = profile.name,
                        surname = profile.surname,
                        selectedLanguage = Language.values()
                            .find { lang -> lang.code == profile.languageCode }
                            ?: Language.ENGLISH,
                        photoUri = profile.photoUri
                    )
                }
            }
        }
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {

            is ProfileEvent.NameChanged ->
                _state.update { it.copy(name = event.name) }

            is ProfileEvent.SurnameChanged ->
                _state.update { it.copy(surname = event.surname) }

            is ProfileEvent.LanguageChanged ->
                _state.update { it.copy(selectedLanguage = event.language) }

            is ProfileEvent.PhotoSelected -> {
                _state.update { it.copy(photoUri = event.uri) }
            }

            ProfileEvent.SaveChanges -> saveProfile()
        }
    }

    private fun saveProfile() {
        viewModelScope.launch {
            val stateValue = _state.value
            repository.saveProfile(
                ProfileEntity(
                    name = stateValue.name,
                    surname = stateValue.surname,
                    languageCode = stateValue.selectedLanguage.code,
                    photoUri = stateValue.photoUri
                )
            )
            _state.update {
                it.copy(isSavedSuccessfully = true)
            }
        }
    }

    fun onSaveMessageShown() {
        _state.update {
            it.copy(isSavedSuccessfully = false)
        }
    }
}

