package com.oguzdogdu.roomexercise.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.parcelize.Parcelize
@Entity(tableName = "users")
data class Users(
    val firstName: String,
    val lastName: String,
    val age: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
) : Parcelable
