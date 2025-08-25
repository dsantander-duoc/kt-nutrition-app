package com.example.mobiledevelopment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            Login(goToRegister = { navController.navigate(Routes.Register) }, goToRecoverPassword = { navController.navigate(Routes.RecoverPassword)}, goBack = { navController.navigateUp()})
        }
        composable(Routes.Register){
            Register(goBack = { navController.navigateUp()})
        }
        composable(Routes.RecoverPassword){
            RecoverPassword(goBack = { navController.navigateUp()})
        }
    }

}