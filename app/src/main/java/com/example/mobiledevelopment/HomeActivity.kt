package com.example.mobiledevelopment

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mobiledevelopment.accessibility.rememberTTS
import com.example.mobiledevelopment.shared.AppDrawer
import com.example.mobiledevelopment.shared.NavBar
import com.example.mobiledevelopment.ui.components.PrimaryButton
import com.example.mobiledevelopment.ui.components.SecondaryButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeWithDrawer(
    userName: String,
    onLogout: () -> Unit,
    onNavigateGenerate: () -> Unit = {},
    onNavigateMyRecipes: () -> Unit = {},
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val isLogged = prefs.getBoolean("isLogged", false)
    val name = prefs.getString("name", null)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                currentRoute = "home",
                onNavigateHome = { scope.launch { drawerState.close() } },
                onNavigateGenerate = {
                    scope.launch { drawerState.close() }
                    onNavigateGenerate()
                },
                onNavigateMyRecipes = {
                    scope.launch { drawerState.close() }
                    onNavigateMyRecipes()
                },
                onLogout = {
                    scope.launch { drawerState.close() }
                    onLogout()
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                NavBar(
                    title = "Hola, $name 👋",
                    screenDescription = "Pantalla de inicio. Hola $name. Acciones disponibles: Generar receta, Mis recetas y Cerrar sesión.",
                    showGoBack = false,
                    hasDrawer = true,
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            }
        ) { padding ->
            HomeContent(
                userName = userName,
                onNavigateGenerate,
                onNavigateMyRecipes,
                contentPadding = padding
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    userName: String,
    onNavigateGenerate: () -> Unit,
    onNavigateMyRecipes: () -> Unit,
    contentPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(horizontal = 24.dp)
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "¿Qué quieres hacer hoy?",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.weight(1f))

        PrimaryButton(
            text = "Generar receta",
            onClick = onNavigateGenerate,
            modifier = Modifier.fillMaxWidth()
        )
        SecondaryButton(
            text = "Mis recetas",
            onClick = onNavigateMyRecipes,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
    }
}