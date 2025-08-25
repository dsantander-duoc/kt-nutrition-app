package com.example.mobiledevelopment

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mobiledevelopment.shared.NavBar
import com.example.mobiledevelopment.ui.components.AccessibleTextField
import com.example.mobiledevelopment.ui.components.LinkButton
import com.example.mobiledevelopment.ui.components.PrimaryButton
import com.example.mobiledevelopment.ui.components.clearFocusOnTapOutside

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecoverPassword(goBack: () -> Unit) {
    val focusManager: FocusManager = LocalFocusManager.current
    var username by remember { mutableStateOf("") }

    Scaffold(topBar = { NavBar(title = "Recuperar clave", screenDescription = "Pantalla de recuperación de contraseña, ingresa tu usuario o email y recibirás un correo con las instrucciones.", goBack = goBack) }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .clearFocusOnTapOutside(focusManager),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AccessibleTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = "Usuario o Email",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    )
                )

                PrimaryButton(
                    text = "Recuperar contraseña",
                    onClick = { /* TODO: send recover password mail */ }
                )

                LinkButton(text = "Volver", onClick = goBack, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}