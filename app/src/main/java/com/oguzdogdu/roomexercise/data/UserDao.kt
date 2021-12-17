package com.oguzdogdu.roomexercise.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.oguzdogdu.roomexercise.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table")
    fun readAllData(): LiveData<List<User>>
}