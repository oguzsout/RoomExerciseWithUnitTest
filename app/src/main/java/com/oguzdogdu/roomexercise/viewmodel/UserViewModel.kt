package com.oguzdogdu.roomexercise.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oguzdogdu.roomexercise.data.UserDatabase
import com.oguzdogdu.roomexercise.model.User
import com.oguzdogdu.roomexercise.repository.UserRepository
import com.oguzdogdu.roomexercise.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>

    private val repository: UserRepository

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
        insertUserMsg.postValue(Resource.success(user))
    }

    private fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
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