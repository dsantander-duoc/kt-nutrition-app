package com.example.mobiledevelopment.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.SpeakerNotes
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import com.example.mobiledevelopment.accessibility.rememberTTS
import com.example.mobiledevelopment.accessibility.speakPolite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(
        title: String,
        screenDescription: String,
        showGoBack: Boolean = true,
        goBack: () -> Unit = {},
        hasDrawer: Boolean = false,
        onMenuClick: () -> Unit = {}
    ) {
    val context = LocalContext.current
    val tts = rememberTTS(context)

    TopAppBar(
        title = { Text(title, modifier = Modifier.semantics { heading() }) },
        navigationIcon = {
            when {
                hasDrawer -> {
                    IconButton(
                        onClick = onMenuClick,
                        modifier = Modifier.semantics {
                            role = Role.Button
                            contentDescription = "Abrir menú de navegación"
                        }
                    ) { Icon(Icons.Filled.Menu, contentDescription = null) }
                }
                showGoBack -> {
                    IconButton(
                        onClick = goBack,
                        modifier = Modifier.semantics {
                            role = Role.Button
                            contentDescription = "Volver"
                        }
                    ) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null) }
                }
            }
        },
        actions = {
            IconButton(
                onClick = { tts.speakPolite(screenDescription) },
                modifier = Modifier.semantics {
                    role = Role.Button
                    contentDescription = "Describir pantalla"
                }
            ) { Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = null) }
        }
    )
}