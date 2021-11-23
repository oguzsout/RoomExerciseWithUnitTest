package com.oguzdogdu.roomexercise.repo

import androidx.lifecycle.MutableLiveData
import com.oguzdogdu.roomexercise.model.User
import com.oguzdogdu.roomexercise.repository.UserRepositoryInterface

class FakeUserRepository : UserRepositoryInterface {

    private val users = mutableListOf<User>()
    private val usersLiveData = MutableLiveData<List<User>>(users)

    override suspend fun addUser(user: User) {
        users.add(user)
    }

    override suspend fun updateUser(user: User) {
        usersLiveData.postValue(users)
    }

    override suspend fun deleteUser(user: User) {
        users.remove(user)
    }
}