package com.mek.tmdbchallange.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mek.tmdbchallange.data.local.dao.ProfileDao
import com.mek.tmdbchallange.data.local.entity.ProfileEntity

@Database(
    entities = [ProfileEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}