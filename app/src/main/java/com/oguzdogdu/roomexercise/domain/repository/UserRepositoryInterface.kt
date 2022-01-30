package com.oguzdogdu.roomexercise.domain.repository

import androidx.lifecycle.LiveData
import com.oguzdogdu.roomexercise.domain.model.Users

interface UserRepositoryInterface {
    fun getUsers() : LiveData<List<Users>>
    suspend fun addUser(users: Users)
    suspend fun updateUser(users: Users)
    suspend fun deleteUser(users: Users)
}