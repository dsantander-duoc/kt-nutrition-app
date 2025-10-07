package com.example.mobiledevelopment.auth_tests

data class LoginResult(
    val ok: Boolean,
    val message: String? = null,
    val user: User? = null
)

data class User(val username: String, val name: String, val password: String)

interface AuthRepository {
    fun login(username: String, password: String): LoginResult
}