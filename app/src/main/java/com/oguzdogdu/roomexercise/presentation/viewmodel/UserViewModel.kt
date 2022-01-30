package com.oguzdogdu.roomexercise.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzdogdu.roomexercise.domain.model.Users
import com.oguzdogdu.roomexercise.domain.repository.UserRepositoryInterface
import com.oguzdogdu.roomexercise.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(  private val repository : UserRepositoryInterface): ViewModel() {

    val userList = repository.getUsers()

    private var insertUserMsg = MutableLiveData<Resource<Users>>()
    val insertUsersMessage: LiveData<Resource<Users>>
        get() = insertUserMsg


    fun makeUser(name: String, lastName: String, age: String) {
        if (name.isEmpty() || lastName.isEmpty() || age.isEmpty()) {
            insertUserMsg.postValue(Resource.error("Enter name, lastname, age", null))
            return
        }
       val userAge = try {
            age.toInt()
        } catch (e: Exception) {
            insertUserMsg.postValue(Resource.error("Age should be number", null))
            return
        }
        val user = Users(name,lastName,userAge)
        addUser(user)
        insertUserMsg.postValue(Resource.success(user))
    }

    private fun addUser(users: Users) {
        viewModelScope.launch {
            repository.addUser(users)
        }
    }

     fun updateUser(users: Users) {
        viewModelScope.launch {
            repository.updateUser(users)
        }
    }

    fun deleteUser(users: Users) {
        viewModelScope.launch {
            repository.deleteUser(users)
        }
    }
}