package com.mek.tmdbchallange.domain.repository

import com.mek.tmdbchallange.data.local.entity.ProfileEntity

interface ProfileRepository {
    suspend fun getProfile(): ProfileEntity?
    suspend fun saveProfile(profile: ProfileEntity)
}