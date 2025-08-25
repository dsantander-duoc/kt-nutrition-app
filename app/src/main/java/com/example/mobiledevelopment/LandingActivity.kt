package com.example.mobiledevelopment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mobiledevelopment.accessibility.rememberTTS
import com.example.mobiledevelopment.accessibility.speakPolite
import com.example.mobiledevelopment.ui.components.PrimaryButton
import com.example.mobiledevelopment.ui.components.SecondaryButton
import com.example.mobiledevelopment.ui.theme.Primary
import com.example.mobiledevelopment.ui.theme.Tertiary

@Composable
fun Landing(goToLogin: () -> Unit, goToRegister: () -> Unit) {
    val gradient = Brush.verticalGradient(listOf(Primary, Tertiary))
    val context = LocalContext.current
    val tts = rememberTTS(context)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(24.dp)
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Bienvenido a NutriGO",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.semantics { heading() }
                    )

                    IconButton(
                        onClick = { tts.speakPolite("Bienvenido a NutriGO, genera recetas accesibles, escúchalas en voz alta y crea listas y crea listas para comenzar a nutrirte!") },
                        modifier = Modifier.semantics {
                            role = Role.Button
                            contentDescription = "Describir pantalla"
                        }
                    ) {
                        Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = null)
                    }
                }

                Text(
                    "Genera recetas accesibles, escúchalas en voz alta y crea listas para comenzar a nutrirte!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(Modifier.height(8.dp))

                PrimaryButton(text = "Iniciar sesión", onClick = goToLogin)

                SecondaryButton(text = "Crear cuenta", onClick = goToRegister)
            }
        }

        Text(
            "Compatible con lector de pantalla",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
        )
    }
}
