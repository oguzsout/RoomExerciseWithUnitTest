package com.oguzdogdu.roomexercise.di

import android.content.Context
import androidx.room.Room
import com.oguzdogdu.roomexercise.data.UserDao
import com.oguzdogdu.roomexercise.data.UserDatabase
import com.oguzdogdu.roomexercise.repository.UserRepository
import com.oguzdogdu.roomexercise.repository.UserRepositoryInterface
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
    fun provideDefaultShoppingRepository(
        dao: UserDao
    ) = UserRepository(dao) as UserRepositoryInterface

    @Singleton
    @Provides
    fun provideUserDao(
        database: UserDatabase
    ) = database.userDao()
}
