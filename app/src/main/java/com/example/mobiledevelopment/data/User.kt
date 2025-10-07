package com.example.mobiledevelopment.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phone: String,
    val birthdate: Long?
)
