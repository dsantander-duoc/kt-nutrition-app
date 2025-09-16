package com.example.mobiledevelopment.helper

import android.util.Patterns
import com.example.mobiledevelopment.model.User

object AuthHelper {
    val users = mutableListOf<User>()

    data class ValidationResult(
        val ok: Boolean,
        val errors: List<String> = emptyList()
    )

    data class LoginResult(
        val ok: Boolean,
        val message: String? = null,
        val user: User? = null
    )

    fun validateRegisterForm(username: String, password: String, name: String, lastName: String, email: String, phone: String, birthdate: Long?): ValidationResult {
        val errors = mutableListOf<String>()
        if (users.any { it.username == username }) {
            errors.add("El nombre de usuario ya está en uso")
        }
        if (users.any { it.email == email }) {
            errors.add("El email ya está en uso")
        }

        if (username.isBlank()) {
            errors.add("El nombre de usuario es requerido")
        }
        if (password.isBlank()) {
            errors.add("La contraseña es requerida")
        }
        if (name.isBlank()) {
            errors.add("El nombre es requerido")
        }
        if (lastName.isBlank()) {
            errors.add("El apellido es requerido")
        }
        if (email.isBlank()) {
            errors.add("El email es requerido")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errors += "El correo no tiene un formato válido."
        }
        if (phone.isBlank()) {
            errors.add("El teléfono es requerido")
        }
        if (birthdate == null) {
            errors.add("La fecha de nacimiento es requerida")
        } else if (birthdate < 0) {
            errors.add("La fecha de nacimiento no es válida")
        } else if (birthdate > System.currentTimeMillis()) {
            errors.add("La fecha de nacimiento no puede ser en el futuro")
        }

        return ValidationResult(errors.isEmpty(), errors)
    }

    fun saveUser(user: User): ValidationResult {
        val valid = validateRegisterForm(user.username, user.password, user.name, user.lastName, user.email, user.phone, user.birthdate)

        if (valid.ok) {
            users.add(user)
            return ValidationResult(true)
        }

        return valid
    }

    fun login(username: String, password: String): LoginResult {
        val user = users.find { it.username == username && it.password == password }
        return if (user != null) {
            LoginResult(true, "Inicio de sesión exitoso",  user)
        }
        else {
            LoginResult(false, "Nombre de usuario o contraseña incorrectos")
        }
    }
}