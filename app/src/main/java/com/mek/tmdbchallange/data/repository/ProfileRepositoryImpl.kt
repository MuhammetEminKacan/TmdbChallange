package com.mek.tmdbchallange.data.repository

import com.mek.tmdbchallange.data.local.dao.ProfileDao
import com.mek.tmdbchallange.data.local.entity.ProfileEntity
import com.mek.tmdbchallange.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDao: ProfileDao
) : ProfileRepository {

    override suspend fun getProfile(): ProfileEntity? {
        return profileDao.getProfile()
    }

    override suspend fun saveProfile(profile: ProfileEntity) {
        profileDao.saveProfile(profile)
    }
}