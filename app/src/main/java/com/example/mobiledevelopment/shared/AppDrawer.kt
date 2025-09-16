package com.example.mobiledevelopment.shared

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun AppDrawer(
    currentRoute: String,
    onNavigateHome: () -> Unit,
    onNavigateGenerate: () -> Unit,
    onNavigateMyRecipes: () -> Unit,
    onLogout: () -> Unit
) {
    ModalDrawerSheet {
        Spacer(Modifier.height(8.dp))
        Text(
            "Menú",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        NavigationDrawerItem(
            label = { Text("Inicio") },
            selected = currentRoute == "home",
            onClick = onNavigateHome,
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Generar receta") },
            selected = currentRoute == "generate",
            onClick = onNavigateGenerate,
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Mis recetas") },
            selected = currentRoute == "my_recipes",
            onClick = onNavigateMyRecipes,
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )

        Spacer(Modifier.weight(1f))

        // Logout at bottom
        NavigationDrawerItem(
            icon = { Icon(Icons.Filled.ExitToApp, contentDescription = null) },
            label = { Text("Cerrar sesión") },
            selected = false,
            onClick = onLogout,
            modifier = Modifier
                .padding(NavigationDrawerItemDefaults.ItemPadding)
                .semantics {
                    role = Role.Button
                    contentDescription = "Cerrar sesión y volver al inicio de sesión"
                }
        )
        Spacer(Modifier.height(8.dp))
    }
}