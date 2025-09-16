package com.example.mobiledevelopment

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mobiledevelopment.helper.AuthHelper
import com.example.mobiledevelopment.model.User
import com.example.mobiledevelopment.shared.DatePickerModal
import com.example.mobiledevelopment.shared.NavBar
import com.example.mobiledevelopment.shared.convertMillisToDate
import com.example.mobiledevelopment.ui.components.AccessibleTextField
import com.example.mobiledevelopment.ui.components.LinkButton
import com.example.mobiledevelopment.ui.components.PrimaryButton
import com.example.mobiledevelopment.ui.components.clearFocusOnTapOutside

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(goBack: () -> Unit) {
    val focusManager: FocusManager = LocalFocusManager.current
    var showModal by remember { mutableStateOf(false) }

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var birthdate by remember { mutableStateOf<Long?>(null) }

    val context = LocalContext.current

    Scaffold(topBar = { NavBar(title = "Registro", screenDescription = "Pantalla de registro, ingresa tus datos para registrarte.", goBack = goBack) }) { padding ->
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
                    label = "Usuario",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                AccessibleTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Contraseña",
                    isPassword = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    )
                )

                AccessibleTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = "Nombre",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                AccessibleTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = "Apellido",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                AccessibleTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

                AccessibleTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = "Teléfono",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    )
                )

                OutlinedTextField(
                    value = birthdate?.let { convertMillisToDate(it) } ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Fecha de nacimiento") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "Seleccionar fecha de nacimiento"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 56.dp)
                        .pointerInput(showModal) {
                            awaitEachGesture {
                                awaitFirstDown(pass = PointerEventPass.Initial)
                                val up = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                                if (up != null) {
                                    showModal = true
                                }
                            }
                        }
                        .semantics {
                            role = Role.Button
                            contentDescription =
                                "Fecha de nacimiento ${birthdate?.let { convertMillisToDate(it) } ?: "sin seleccionar"}"
                        }
                )

                PrimaryButton(text = "Registrarme", onClick = {
                    val result = AuthHelper.saveUser(User(username, password, name, lastName, email, phone, birthdate))
                    if (result.ok) {
                        goBack()
                    }
                    else {
                        result.errors.forEach {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        }
                    }
                })

                LinkButton(text = "Volver", onClick = goBack, modifier = Modifier.fillMaxWidth())
            }

            if (showModal) {
                DatePickerModal(
                    onDateSelected = { birthdate = it },
                    onDismiss = { showModal = false }
                )
            }
        }
    }
}
