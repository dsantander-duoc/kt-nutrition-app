package com.example.mobiledevelopment

import com.example.mobiledevelopment.auth_tests.AuthRepository
import com.example.mobiledevelopment.auth_tests.LoginResult
import com.example.mobiledevelopment.auth_tests.LoginViewModel
import com.example.mobiledevelopment.auth_tests.User
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.*

class LoginViewModelTest {

    // Se usa un repositorio simulado (mock) para no depender de base de datos real
    private val repo = mock<AuthRepository>()
    private val viewModel = LoginViewModel(repo)

    @Test
    fun `campos vacíos`() {
        // Caso: usuario y contraseña en blanco
        val result = viewModel.login("", "")

        // Se espera que el login falle y devuelva el mensaje correcto
        assertFalse(result.ok)
        assertEquals("Campos vacíos", result.message)

        // Se verifica que nunca se haya llamado al repositorio
        verify(repo, never()).login(any(), any())
    }

    @Test
    fun `login correcto`() {
        // Caso: credenciales válidas
        val user = User("dsantander", "Daniel", "123456")

        // Se prepara el mock para que devuelva éxito con este usuario
        whenever(repo.login("dsantander", "123456"))
            .thenReturn(LoginResult(true, user = user))

        // Se ejecuta el login
        val result = viewModel.login("dsantander", "123456")

        // Se espera éxito y que el usuario no sea nulo
        assertTrue(result.ok)
        assertNotNull(result.user)
        assertEquals("dsantander", result.user?.username)

        // Verificar que el repositorio fue invocado correctamente
        verify(repo).login("dsantander", "123456")
    }
}
