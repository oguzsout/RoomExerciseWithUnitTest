package com.oguzdogdu.roomexercise.repository

import androidx.lifecycle.LiveData
import com.oguzdogdu.roomexercise.model.User

interface UserRepositoryInterface {
    fun getUsers() : LiveData<List<User>>
    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
}