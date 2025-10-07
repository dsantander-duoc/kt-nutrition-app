package com.example.mobiledevelopment.auth_tests

class LoginViewModel(private val repo: AuthRepository) {

    fun login(username: String, password: String): LoginResult {
        if (username.isBlank() || password.isBlank()) {
            return LoginResult(false, "Campos vacíos")
        }

        val result = repo.login(username, password)

        return if (result.ok) {
            LoginResult(true, user = result.user)
        } else {
            result
        }
    }
}