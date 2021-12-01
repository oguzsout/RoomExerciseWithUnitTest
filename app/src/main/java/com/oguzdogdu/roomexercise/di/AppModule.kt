package com.oguzdogdu.roomexercise.di

import android.content.Context
import androidx.room.Room
import com.oguzdogdu.roomexercise.data.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, UserDatabase::class.java, "user_db").build()

    @Singleton
    @Provides
    fun injectDao(
        database: UserDatabase
    ) = database.userDao()

}