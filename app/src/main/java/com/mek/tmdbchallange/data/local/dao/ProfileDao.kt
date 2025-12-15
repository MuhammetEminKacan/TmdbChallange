package com.mek.tmdbchallange.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mek.tmdbchallange.data.local.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile WHERE id = 1")
    suspend fun getProfile(): ProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(profile: ProfileEntity)

    @Query("SELECT languageCode FROM profile WHERE id = 1")
    fun observeLanguage(): Flow<String?>

    @Query("UPDATE profile SET photoUri = :uri WHERE id = 1")
    suspend fun updatePhoto(uri: String)
}