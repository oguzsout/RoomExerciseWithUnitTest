package com.oguzdogdu.roomexercise.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.oguzdogdu.roomexercise.domain.model.Users

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(users: Users)

    @Update
    suspend fun updateUser(users: Users)

    @Delete
    suspend fun deleteUser(users: Users)

    @Query("SELECT * FROM users")
    fun readAllData(): LiveData<List<Users>>
}