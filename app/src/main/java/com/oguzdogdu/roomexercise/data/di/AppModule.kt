package com.oguzdogdu.roomexercise.data.di

import android.content.Context
import androidx.room.Room
import com.oguzdogdu.roomexercise.data.local.UserDao
import com.oguzdogdu.roomexercise.data.local.UserDatabase
import com.oguzdogdu.roomexercise.data.repository.UserRepository
import com.oguzdogdu.roomexercise.domain.repository.UserRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, UserDatabase::class.java, "user_db").fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideRepository(
        dao: UserDao
    ) = UserRepository(dao) as UserRepositoryInterface

    @Singleton
    @Provides
    fun provideUserDao(
        database: UserDatabase
    ) = database.userDao()
}
