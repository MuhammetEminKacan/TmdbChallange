package com.mek.tmdbchallange.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey
    val id: Int = 1,
    val name: String,
    val surname: String,
    val languageCode: String,
    val photoUri: String?
)