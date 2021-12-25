package com.oguzdogdu.roomexercise.data.repository

import androidx.lifecycle.LiveData
import com.oguzdogdu.roomexercise.data.local.UserDao
import com.oguzdogdu.roomexercise.domain.model.User
import com.oguzdogdu.roomexercise.domain.repository.UserRepositoryInterface

class UserRepository(private val userDao: UserDao): UserRepositoryInterface {

    override fun getUsers(): LiveData<List<User>> {
       return userDao.readAllData()
    }

    override suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}