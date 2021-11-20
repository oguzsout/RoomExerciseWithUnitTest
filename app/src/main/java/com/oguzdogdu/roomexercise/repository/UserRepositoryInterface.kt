package com.oguzdogdu.roomexercise.repository

import com.oguzdogdu.roomexercise.model.User

interface UserRepositoryInterface {
    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun deleteAllUsers()
}