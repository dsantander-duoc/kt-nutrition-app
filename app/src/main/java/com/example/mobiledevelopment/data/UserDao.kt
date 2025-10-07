package com.example.mobiledevelopment.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM user WHERE username = :username LIMIT 1")
    suspend fun searchByUsername(username: String): User?

    @Query("SELECT * FROM user WHERE username = :email LIMIT 1")
    suspend fun searchByEmail(email: String): User?
}
