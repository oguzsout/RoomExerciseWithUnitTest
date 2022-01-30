package com.oguzdogdu.roomexercise.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oguzdogdu.roomexercise.domain.model.Users

@Database(entities = [Users::class],version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}