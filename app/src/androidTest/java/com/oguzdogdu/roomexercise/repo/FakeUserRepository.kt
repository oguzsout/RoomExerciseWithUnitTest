package com.oguzdogdu.roomexercise.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oguzdogdu.roomexercise.domain.model.Users
import com.oguzdogdu.roomexercise.domain.repository.UserRepositoryInterface

class FakeUsersRepository : UserRepositoryInterface {

    private val users = mutableListOf<Users>()
    private val usersLiveData = MutableLiveData<List<Users>>(users)

    override fun getUsers(): LiveData<List<Users>> {
        return usersLiveData
    }

    override suspend fun addUser(users: Users) {
        this.users.add(users)
        refreshLiveData()
    }

    override suspend fun updateUser(users: Users) {
        usersLiveData.postValue(this.users)
    }

    override suspend fun deleteUser(users: Users) {
        this.users.remove(users)
        refreshLiveData()
    }

    private fun refreshLiveData() {
        usersLiveData.postValue(users)
    }
}