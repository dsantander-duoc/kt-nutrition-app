package com.example.mobiledevelopment

object Routes {
    const val Landing = "landing"
    const val Login = "login"
    const val Register = "register"
    const val RecoverPassword = "recoverPassword"
    const val Home = "home"
}

object HomeDest {
    const val route = Routes.Home
    const val argUser = "userName"
    // Ruta patrón con argumento
    const val fullRoute = "$route/{$argUser}"

    fun create(userName: String) = "$route/${android.net.Uri.encode(userName)}"
}