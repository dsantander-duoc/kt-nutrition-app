package com.example.mobiledevelopment.helper

import android.content.Context
import android.util.Patterns
import com.example.mobiledevelopment.data.AppDatabase
import com.example.mobiledevelopment.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AuthHelper {

    data class ValidationResult(
        val ok: Boolean,
        val errors: List<String> = emptyList()
    )

    data class LoginResult(
        val ok: Boolean,
        val message: String? = null,
        val user: User? = null
    )

    suspend fun validateRegisterForm(context: Context, username: String, password: String, name: String, lastName: String, email: String, phone: String, birthdate: Long?): ValidationResult {
        val errors = mutableListOf<String>()

        withContext(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(context)
            val userExists = db.userDao().searchByUsername(username)
            if (userExists != null) {
                errors.add("El nombre de usuario ya está en uso")
            }

            val emailExists = db.userDao().searchByEmail(email)
            if (emailExists != null) {
                errors.add("El email ya está en uso")
            }
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

    suspend fun saveUser(context: Context, user: User): ValidationResult {
        val valid = validateRegisterForm(context,user.username, user.password, user.name, user.lastName, user.email, user.phone, user.birthdate)

        if (!valid.ok) {
            return valid
        }

        return withContext(Dispatchers.IO) {
            try {
                val db = AppDatabase.getDatabase(context)
                db.userDao().insert(user)
                ValidationResult(true)
            } catch (e: Exception) {
                e.printStackTrace()
                ValidationResult(false, listOf("Error al guardar el usuario"))
            }
        }
    }

    suspend fun login(context: Context, username: String, password: String): LoginResult {
        return withContext(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(context)
            val usuario = db.userDao().searchByUsername(username)

            if (usuario == null) {
                LoginResult(false, "Nombre de usuario no encontrado")
            } else if (usuario.password != password) {
                LoginResult(false, "Contraseña incorrecta")
            } else {

                val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                prefs.edit()
                    .putBoolean("isLogged", true)
                    .putString("email", usuario.email)
                    .putString("name", usuario.name)
                    .apply()

                LoginResult(true, "Inicio de sesión exitoso",  usuario)
            }
        }
    }
}
