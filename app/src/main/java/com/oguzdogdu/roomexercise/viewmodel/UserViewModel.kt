package com.oguzdogdu.roomexercise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzdogdu.roomexercise.model.User
import com.oguzdogdu.roomexercise.repository.UserRepositoryInterface
import com.oguzdogdu.roomexercise.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(  private val repository : UserRepositoryInterface): ViewModel() {

    val userList = repository.getUsers()

    private var insertUserMsg = MutableLiveData<Resource<User>>()
    val insertUserMessage: LiveData<Resource<User>>
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
        val user = User(name,lastName,userAge)
        addUser(user)
        insertUserMsg.postValue(Resource.success(user))
    }

    private fun addUser(user: User) {
        viewModelScope.launch {
            repository.addUser(user)
        }
    }

     fun updateUser(user: User) {
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }
}