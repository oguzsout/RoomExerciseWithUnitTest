package com.oguzdogdu.roomexercise.data.repository

import androidx.lifecycle.LiveData
import com.oguzdogdu.roomexercise.data.local.UserDao
import com.oguzdogdu.roomexercise.domain.model.Users
import com.oguzdogdu.roomexercise.domain.repository.UserRepositoryInterface

class UserRepository(private val userDao: UserDao): UserRepositoryInterface {

    override fun getUsers(): LiveData<List<Users>> {
       return userDao.readAllData()
    }

    override suspend fun addUser(users: Users) {
        userDao.addUser(users)
    }

    override suspend fun updateUser(users: Users) {
        userDao.updateUser(users)
    }

    override suspend fun deleteUser(users: Users) {
        userDao.deleteUser(users)
    }
}