package com.example.mobiledevelopment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobiledevelopment.helper.AuthHelper
import com.example.mobiledevelopment.model.User
import com.example.mobiledevelopment.ui.theme.NutriGoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NutriGoTheme {
                AppNavigation()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Routes.Landing){
        composable(Routes.Landing) {
            Landing(
                goToLogin = { navController.navigate(Routes.Login) },
                goToRegister = { navController.navigate(Routes.Register) }
            )
        }
        composable(Routes.Login){
            Login(goToRegister = { navController.navigate(Routes.Register) }, goToRecoverPassword = { navController.navigate(Routes.RecoverPassword)}, goBack = { navController.navigateUp()},
                goHome={ userName ->
                    navController.navigate(HomeDest.create(userName)) {
                        popUpTo(Routes.Landing) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(Routes.Register){
            Register(goBack = { navController.navigateUp()})
        }
        composable(Routes.RecoverPassword){
            RecoverPassword(goBack = { navController.navigateUp()})
        }
        composable(
            route = HomeDest.fullRoute,
            arguments = listOf(navArgument(HomeDest.argUser) { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString(HomeDest.argUser) ?: "Invitado"

            HomeWithDrawer(
                userName = userName,
                onLogout = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Landing) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            )
        }
    }

}