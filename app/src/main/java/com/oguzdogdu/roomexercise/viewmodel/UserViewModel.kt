package com.oguzdogdu.roomexercise.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.oguzdogdu.roomexercise.data.UserDatabase
import com.oguzdogdu.roomexercise.model.User
import com.oguzdogdu.roomexercise.repository.UserRepository
import com.oguzdogdu.roomexercise.repository.UserRepositoryInterface
import com.oguzdogdu.roomexercise.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(  private val repository : UserRepositoryInterface): ViewModel() {

    val userList = repository.

    private val insertUserMsg = MutableLiveData<Resource<User>>()
    val insertUserMessage: LiveData<Resource<User>>
        get() = insertUserMsg

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

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
        val user = User(0, name, lastName, userAge)
        addUser(user)
        updateUser(user)
        insertUserMsg.postValue(Resource.success(user))
    }

    private fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    private fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }
}