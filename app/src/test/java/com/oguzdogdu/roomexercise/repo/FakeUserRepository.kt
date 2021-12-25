package com.oguzdogdu.roomexercise.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oguzdogdu.roomexercise.domain.model.User
import com.oguzdogdu.roomexercise.domain.repository.UserRepositoryInterface

class FakeUserRepository : UserRepositoryInterface {

    private val users = mutableListOf<User>()
    private val usersLiveData = MutableLiveData<List<User>>(users)

    override fun getUsers(): LiveData<List<User>> {
        return usersLiveData
    }

    override suspend fun addUser(user: User) {
        users.add(user)
        refreshLiveData()
    }

    override suspend fun updateUser(user: User) {
        usersLiveData.postValue(users)
    }

    override suspend fun deleteUser(user: User) {
        users.remove(user)
        refreshLiveData()
    }

    private fun refreshLiveData() {
        usersLiveData.postValue(users)
    }
}