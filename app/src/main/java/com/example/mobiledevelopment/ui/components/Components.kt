package com.example.mobiledevelopment.ui.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp

// outside tap clear focus
fun Modifier.clearFocusOnTapOutside(focusManager: FocusManager): Modifier =
    pointerInput(Unit) {
        detectTapGestures(onTap = { focusManager.clearFocus() })
    }

// accessible text field with error
@Composable
fun AccessibleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    error: String? = null,
    imeAction: ImeAction = ImeAction.Next
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val visualTransformation =
        if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None

    val trailing: (@Composable () -> Unit)? =
        if (isPassword) {
            {
                val desc = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = desc
                    )
                }
            }
        } else null

    Column(
        modifier = modifier
            .semantics {
                contentDescription = label
                if (error != null) error(error)
            }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            singleLine = true,
            visualTransformation = visualTransformation,
            trailingIcon = trailing,
            keyboardOptions = keyboardOptions.copy(imeAction = imeAction),
            isError = error != null,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp) // touch target big
        )
        if (error != null) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite } // TTS
            )
        }
    }
}

// Primary Button accesible
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .semantics {
                role = Role.Button
                contentDescription = text
            }
    ) { Text(text) }
}

// Secondary Button accesible (outlined)
@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .semantics { role = Role.Button; contentDescription = text }
    ) { Text(text) }
}

// TextButton accesible
@Composable
fun LinkButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .semantics { role = Role.Button; contentDescription = text }
    ) { Text(text) }
}
